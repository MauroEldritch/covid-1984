#!/usr/bin/lua
-- Tool for detecting Propaganda Behavior on Twitter Profiles and Groups.
-- Part of my talk "COVID-1984: Propaganda and Surveillance during a Pandemic".
-- Requires lua5.1.
-- Requires lua-twitter: https://github.com/leafo/lua-twitter or `luarocks install https://luarocks.org/manifests/leafo/twitter-dev-1.rockspec`.
-- Requires lunajson: `luarocks install lunajson`.
-- Mauro Eldritch, 2020. https://github.com/mauroeldritch/COVID-1984.

-- Configuration. Request your Keys @ https://developer.twitter.com/en.
twitter_api_key =       ""
twitter_api_secret =    ""
twitter_token_key =     ""
twitter_token_secret =  ""
-- Default: 468739 Buenos Aires, Argentina. Get location IDs @ https://api.twitter.com/1.1/trends/place.json.
trends_location =       "468739"
-- Actors List. Accounts relevant to the investigation, to check if users follow or RT them. Must be lowercase.
actors_list =           {"mauroeldritch"}

-- Auxiliary Functions
function api_init()
    lunajson = require 'lunajson'
    Twitter = require("twitter").Twitter
    Twitter = Twitter({
        consumer_key = twitter_api_key,
        consumer_secret = twitter_api_secret,
        access_token = twitter_token_key,
        access_token_secret = twitter_token_secret
    })
end

function banner()
    print("Venator.lua\n\t@mauroeldritch (plaguedoktor) - 2020\n")
end

function help()
    banner()
    print("[*] Usage: ./venator.lua [Action] [Target]")
    print("[*] Examples (Using 'mauroeldritch' as username):")
    print("\tuser mauroeldritch\t\tGet information about given user (without @).")
    print("\tuser_json mauroeldritch\t\tGet information about given user (without @). JSON Output.")
    print("\thashtags mauroeldritch\t\tAnalyze tweets from a given user (without @).")
    print("\thashtags_json mauroeldritch\tAnalyze tweets from a given user (without @). JSON Output.")
    print("\tinteractions mauroeldritch\tAnalyze interactions from a given user (without @).")
    print("\tinteractions mauroeldritch\tAnalyze interactions from a given user (without @). JSON Output.")
    print("\tfollows mauroeldritch\t\tCheck if user follows accounts in the Actors List.")
    print("\tfollows_json mauroeldritch\tCheck if user follows accounts in the Actors List. JSON Output.")
    print("\ttrends\t\t\t\tList current Trending Topics in the configured region.")
    print("\ttrends_json\t\t\tList current Trending Topics in the configured region. JSON Output.")
    print("\tsearch #hashtag\t\t\tSearch Latest and Popular Tweets with a given hashtag.")
    print("\tsearch_json #hashtag\t\tSearch Latest and Popular Tweets with a given hashtag. JSON Output.")
    print("")
    os.exit()
end

function os.capture(cmd, raw)
    local handle = assert(io.popen(cmd, 'r'))
    local output = assert(handle:read('*a'))
    handle:close()
    if raw then 
        return output 
    end
    output = string.gsub(
        string.gsub(
            string.gsub(output, '^%s+', ''), 
            '%s+$', 
            ''
        ), 
        '[\n\r]+',
        ' '
    )
    return output
end

function get_oauth_token()
    local command = os.capture('curl -s -X POST "https://api.twitter.com/oauth2/token" -H "Content-Type: application/x-www-form-urlencoded;charset=UTF-8" -u '..twitter_api_key..':'..twitter_api_secret..' --data-urlencode "grant_type=client_credentials"')
    local L="return "..command:gsub('("[^"]-"):','[%1]=')
    local T=loadstring(L)()
    return T.access_token
end

function table.contains(table, element)
    for _, value in pairs(table) do
        if value == element then
            return true
        end
    end
    return false
end

-- Application Endpoints
function get_user(username)
    banner()
    local tusr = Twitter:get_user({screen_name = username})
    local tooltip_username = "[!] Username contains many numbers. This may indicate a default username given by the platform."
    local tooltip_created = "[!] This account was recently created."
    local tooltip_default_profile = "[!] This account has default profile settings enabled."
    local tooltip_default_image = "[!] This account has a default profile picture."
    local tooltip_following_count = "[!] This account has fewer Followers than Followed."
    local tooltip_followers_count = "[!] This account has less than 50 Followers."
    local tooltip_tweets = "[!] This account has less than 10 tweets, which may indicate a lurker/dormant account."
    local tooltip_verified = "[?] This account is not verified. This does not affect the Final Score."
    local t_username, t_created, t_defprof, t_defimg, t_followc, t_followrc, t_tweets, t_verified
    local score = 0, year_created
    -- Check for lots of numbers in username
    function count_username_numbers(s,p)
        local _,n=s:gsub(p," ")
        return n
    end
    local numbers_in_username = count_username_numbers(username,"%d")
    if numbers_in_username > 4 then sym = "!"; score = score + 10; t_username = true; else sym = "*" end
    print("[" ..sym.. "] Data for user: ", username)
    -- Check if the account was created less than a year ago
    for w in  string.gmatch (tusr.created_at, "(%d*%.?%d+)") do year_created = w; end
    if (os.date("%Y") - year_created < 1) then sym = "!"; score = score + 10; t_created = true; else sym = "*" end
    print("[" ..sym.. "] Created at: ", tusr.created_at)
    -- Check if profile has default settings
    if tusr.default_profile == true then sym = "!"; score = score + 20; t_defprof = true; else sym = "*" end
    print("[" ..sym.. "] Default Profile: ", tusr.default_profile)
    -- Check if profile has default avatar
    if tusr.default_profile_image == true then sym = "!"; score = score + 20; t_defimg = true; else sym = "*" end
    print("[" ..sym.. "] Default Image: ", tusr.default_profile_image)
    -- Check if Followers are less than Following
    if (tusr.followers_count < tusr.friends_count) then sym = "!"; score = score + 15; t_followc = true; else sym = "*" end
    print("[" ..sym.. "] Following Count: ", tusr.friends_count)
    -- Check if Followers are less than 50
    if tusr.followers_count < 50 then sym = "!"; score = score + 15; t_followrc = true; else sym = "*" end
    print("[" ..sym.. "] Followers Count: ", tusr.followers_count)
    -- Check if Tweets are less than 10 (Dormant)
    if tusr.statuses_count < 10 then sym = "!"; score = score + 10; t_tweets = true; else sym = "*" end
    print("[" ..sym.. "] Tweets Count: ", tusr.statuses_count)
    -- Check if account is Verified
    if tusr.verified == false then sym = "?"; t_verified = true; else sym = "*"; score = score -70 end
    print("[" ..sym.. "] Verified Profile: ", tusr.verified)
    -- Final Score
    if score < 40 then sym = "*" else sym = "!" end
    print("\n[" ..sym.. "] Final Score: " ..score.. "/100.\n")
    -- Tooltips
    if t_username == true then print(tooltip_username) end
    if t_created == true then print(tooltip_created) end
    if t_defprof == true then print(tooltip_default_profile) end
    if t_defimg == true then print(tooltip_default_image) end
    if t_followc == true then print(tooltip_following_count) end
    if t_followrc == true then print(tooltip_followers_count) end
    if t_tweets == true then print(tooltip_tweets) end
    if t_verified == true then print(tooltip_verified) end
end

function get_user_json(username)
    local tusr = Twitter:get_user({screen_name = username})
    local score = 0, year_created
    -- Check for lots of numbers in username
    function count_username_numbers(s,p)
        local _,n=s:gsub(p," ")
        return n
    end
    local numbers_in_username = count_username_numbers(username,"%d")
    if numbers_in_username > 4 then score = score + 10; end
    -- Check if the account was created less than a year ago
    for w in  string.gmatch (tusr.created_at, "(%d*%.?%d+)") do year_created = w; end
    if (os.date("%Y") - year_created < 1) then score = score + 10 end
    -- Check if profile has default settings
    if tusr.default_profile == true then score = score + 20 end
    -- Check if profile has default avatar
    if tusr.default_profile_image == true then score = score + 20 end
    -- Check if Followers are less than Following
    if (tusr.followers_count < tusr.friends_count) then score = score + 15 end
    -- Check if Followers are less than 50
    if tusr.followers_count < 50 then score = score + 15 end
    -- Check if Tweets are less than 10 (Dormant)
    if tusr.statuses_count < 10 then score = score + 10 end
    -- Check if account is Verified
    if tusr.verified == true then score = score -70 end
    local json_output = '[{"username": "'..username..'", "numbers_in_handler": "'..numbers_in_username..'", "created": "'..year_created..'", "default_settings": "'..tostring(tusr.default_profile)..'", "default_avatar": "'..tostring(tusr.default_profile_image)..'", "followers": "'..tostring(tusr.followers_count)..'", "following": "'..tostring(tusr.friends_count)..'", "tweets": "'..tostring(tusr.statuses_count)..'", "verified": "'..tostring(tusr.verified)..'", "score": "'..score..'"}]'
    print(json_output)
end

function get_hashtags(username)
    banner()
    local hashtags = {}
    print("[*] Hashtags used by: ", username)
    for tweet in Twitter:user_timeline_each_tweet({ screen_name = username }) do
        for w in string.gmatch(tweet.text, '#%w+') do
            table.insert(hashtags, w:lower())
        end
    end
    table.sort(hashtags)
    table.insert(hashtags, "EOF")
    lastvalue = hashtags[1]
    lastquantity = 0
    for i=1, #hashtags do
        if lastvalue == hashtags[i] then
            lastquantity = lastquantity + 1
        else
            print(lastvalue.. ": "..lastquantity)
            lastquantity = 1
            lastvalue = hashtags[i]
        end
    end
end

function get_hashtags_json(username)
    local hashtags = {}
    for tweet in Twitter:user_timeline_each_tweet({ screen_name = username }) do
        for w in string.gmatch(tweet.text, '#%w+') do
            table.insert(hashtags, w:lower())
        end
    end
    table.sort(hashtags)
    table.insert(hashtags, "EOF")
    lastvalue = hashtags[1]
    lastquantity = 0
    json_output = ""
    for i=1, #hashtags do
        if lastvalue == hashtags[i] then
            lastquantity = lastquantity + 1
        else
            json_output = json_output .. '{"hashtag": "'..lastvalue..'", "count": "'..lastquantity..'"},'
            lastquantity = 1
            lastvalue = hashtags[i]
        end
    end
    json_output = "[" .. json_output:sub(1, -2) .. "]"
    print(json_output)
end

function get_interactions(username)
    banner()
    local interactions = {}
    print("[*] Interactions from: ", username)
    for tweet in Twitter:user_timeline_each_tweet({ screen_name = username }) do
        if tostring(tweet.in_reply_to_screen_name) ~= "userdata: (nil)" then
            table.insert(interactions, tostring(tweet.in_reply_to_screen_name):lower())
        end
    end
    table.sort(interactions)
    table.insert(interactions, "EOF")
    lastvalue = interactions[1]
    lastquantity = 0
    for i=1, #interactions do
        if lastvalue == interactions[i] then
            lastquantity = lastquantity + 1
        else
            print(lastvalue.. ": "..lastquantity)
            lastquantity = 1
            lastvalue = interactions[i]
        end
    end
end

function get_interactions_json(username)
    local interactions = {}
    for tweet in Twitter:user_timeline_each_tweet({ screen_name = username }) do
        if tostring(tweet.in_reply_to_screen_name) ~= "userdata: (nil)" then
            table.insert(interactions, tostring(tweet.in_reply_to_screen_name):lower())
        end
    end
    table.sort(interactions)
    table.insert(interactions, "EOF")
    lastvalue = interactions[1]
    lastquantity = 0
    json_output = ""
    for i=1, #interactions do
        if lastvalue == interactions[i] then
            lastquantity = lastquantity + 1
        else
            json_output = json_output .. '{"user": "'..lastvalue..'", "count": "'..lastquantity..'"},'
            lastquantity = 1
            lastvalue = interactions[i]
        end
    end
    json_output = "[" .. json_output:sub(1, -2) .. "]"
    print(json_output)
end

function get_trends()
    banner()
    print("[*] Trending Topics:")
    local oauth_token = get_oauth_token()
    local command = os.capture('curl -s -X GET -H "Authorization: Bearer '..oauth_token..'" "https://api.twitter.com/1.1/trends/place.json?id='..trends_location..'"')
    local jsonparse = lunajson.decode(command)
    for i=1, 35 do
        local name = jsonparse[1]["trends"][i]["name"]
        local volume = jsonparse[1]["trends"][i]["tweet_volume"]
        if volume == nil then
            volume = "Unknown"
        end
        print("[*] "..name..": "..volume)
    end
end

function get_trends_json()
    local oauth_token = get_oauth_token()
    local command = os.capture('curl -s -X GET -H "Authorization: Bearer '..oauth_token..'" "https://api.twitter.com/1.1/trends/place.json?id='..trends_location..'"')
    print(command)
end

function get_tweets_by_hashtag(hashtag)
    banner()
    print("[*] Tweets containing: ", hashtag)
    local oauth_token = get_oauth_token()
    local query = hashtag
    local command = os.capture('curl -s -X GET -H "Authorization: Bearer '..oauth_token..'" "https://api.twitter.com/1.1/search/tweets.json?q='..query..'&count=20"')
    local jsonparse = lunajson.decode(command)
    for i=1, 20 do
        local name = jsonparse["statuses"][i]["user"]["screen_name"]
        local followers = jsonparse["statuses"][i]["user"]["followers_count"]
        local friends = jsonparse["statuses"][i]["user"]["friends_count"]
        local rts = jsonparse["statuses"][i]["retweet_count"]
        local fav = jsonparse["statuses"][i]["favorite_count"]
        local txt = jsonparse["statuses"][i]["text"]
        print("[*] @"..name.." ("..followers.."|"..friends..") [RTs: "..rts.."] [Fav: "..fav.."]: "..txt)
    end
end

function get_tweets_by_hashtag_json(hashtag)
    local oauth_token = get_oauth_token()
    local query = hashtag
    local command = os.capture('curl -s -X GET -H "Authorization: Bearer '..oauth_token..'" "https://api.twitter.com/1.1/search/tweets.json?q='..query..'&count=100"')
    print(command)
end

function get_relevant_follows(username)
    banner()
    print("[*] Checking if "..username.." follows accounts in Actors List:")
    local actors_followed = 0
    local oauth_token = get_oauth_token()
    local command = os.capture('curl -s -X GET -H "Authorization: Bearer '..oauth_token..'" "https://api.twitter.com/1.1/friends/list.json?screen_name='..username..'&skip_status=true&count=200"')
    local jsonparse = lunajson.decode(command)
    for i=1, #jsonparse["users"] do
        local name = jsonparse["users"][i]["screen_name"]:lower()
        if table.contains(actors_list, name) then
            print("[!] @"..username.." follows "..name..".")
            actors_followed = actors_followed + 1
        end
    end
    if actors_followed == 0 then
        print("[*] @"..username.." does not follow any listed actor.")
    end
end

function get_relevant_follows_json(username)
    local oauth_token = get_oauth_token()
    local command = os.capture('curl -s -X GET -H "Authorization: Bearer '..oauth_token..'" "https://api.twitter.com/1.1/friends/list.json?screen_name='..username..'&skip_status=true&count=200"')
    local jsonparse = lunajson.decode(command)
    json_output = ""
    for i=1, #jsonparse["users"] do
        local name = jsonparse["users"][i]["screen_name"]:lower()
        if table.contains(actors_list, name) then
            json_output = json_output..'{"actor": "'..name..'"},'
        end
    end
    json_output = "[" .. json_output:sub(1, -2) .. "]"
    print(json_output)
end

function main()
    os.execute("clear")
    if (#arg == 0 or arg[1] == "help") then help() end
    if (arg[1] == "user" and arg[2] ~= nil) then 
        get_user(arg[2])
    elseif (arg[1] == "user_json" and arg[2] ~= nil) then
        get_user_json(arg[2])
    elseif (arg[1] == "hashtags" and arg[2] ~= nil) then
        get_hashtags(arg[2])
    elseif (arg[1] == "hashtags_json" and arg[2] ~= nil) then
        get_hashtags_json(arg[2])
    elseif (arg[1] == "interactions" and arg[2] ~= nil) then
        get_interactions(arg[2])
    elseif (arg[1] == "interactions_json" and arg[2] ~= nil) then
        get_interactions_json(arg[2])
    elseif (arg[1] == "trends") then
        get_trends(arg[2])
    elseif (arg[1] == "trends_json") then
        get_trends_json(arg[2])
    elseif (arg[1] == "search" and arg[2] ~= nil) then
        get_tweets_by_hashtag(arg[2])
    elseif (arg[1] == "search_json" and arg[2] ~= nil) then
        get_tweets_by_hashtag_json(arg[2])
    elseif (arg[1] == "follows" and arg[2] ~= nil) then
        get_relevant_follows(arg[2])
    elseif (arg[1] == "follows_json" and arg[2] ~= nil) then
        get_relevant_follows_json(arg[2])
    else
        banner()
        print("[!] Error: Incorrect syntax. Please run ./venator.lua help for usage information.")
    end
end

api_init()
main()