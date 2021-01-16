# COVID-1984

This is the repository for my talk "COVID-1984: Propaganda and Surveillance during a Pandemic".

## Folders structure
- **APKs:** 'ar.gob.coronavirus' V1 and V3 APKs.
- **Decompiled:** Java Source Code extracted with Jadx & APKTool. Contains only specific pieces of code, not the entire dump.
- **Logs:** Sandbox Debug Logs.
- **Reports:** Original reports from Immuniweb, OstorLab and SandDroid.
- **Docs:** Original Whitepaper (Spanish). Media and Support files for Venator Tool.
- **venator.lua:** Venator Tool, Propaganda Behavior detector on Twitter Profiles and Groups. Written for this talk. This is a limited version.

## Venator.lua

![Venator](https://github.com/mauroeldritch/COVID-1984/blob/master/Docs/Venator_Small.png)

Venator is a tool written in LUA, which aids in the identification of propaganda-related behavior in Twitter accounts.
It looks for suspicious things like default profile pictures and settings, having fewer followers than followed users, recently created accounts, having lots of random numbers in the handler (as assigned by default by Twitter), interactions between users, hashtags used, and more.
It requires a Twitter API Key in order to work (can be issued instantly and for free), and can be used interactively or automatized.
Venator was written for and used in this talk. Remember that Twitter ToS do not support using their API for surveillance or intelligence purposes.

### Venator.lua Usage
Simply run Venator issuing `./venator.lua help #or lua venator.lua help` to get usage information.
*Note*: Trends Location and Actors are manually set to Argentina. Can be changed in the configuration block inside Venator.lua.

## Presentations
|#| Date | Conference |  Link to Video | Link to Slides |
|---|---|---|---|---|
|1| 27-JUN-2020 | DEF CON Red Team Village / Junegle Cyber Summit / Texas Cyber Summit | https://www.youtube.com/watch?v=2ldvZVAQHTQ | https://docs.google.com/presentation/d/1_l5ZL211PQJewNxMAMj6SnYUCjRmftZp11MPLdu_nvc/edit?usp=sharing |
|2| 08-AUG-2020 | DEF CON Recon Village | https://youtu.be/Qqy1RiasOR4 | https://docs.google.com/presentation/d/1hrctTt6m8VciiMFrq_Nm2aiAs3AevbhEAKI_MWcCg9k/edit?usp=sharing |
|3| 08-NOV-2020 | HoneyCON España | - | https://docs.google.com/presentation/d/1e_4SnjnesWLsFaiwPvoLJWIoy1EtI-uFFfyYVbmNvE4/edit#slide=id.p3 |
|4| 16-ENE-2021 | CONHESI Perú | https://www.youtube.com/watch?v=UiQqdZAI8hY | https://docs.google.com/presentation/d/1doYRjExLH7zbVQxdgHMvlhBhw2aMzhCfkXj2PQPeDeg/edit?usp=sharing |
