package com.globant.pasaportesanitario.utils.token;

import c.c.a.e.g.a;
import d.a.a.c;
import d.a.a.d;
import f.l.c.g;
import h.a.a.a.b.b;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class TokenUtils {
    static {
        System.loadLibrary("native-lib");
    }

    public static a a() {
        byte[] bArr;
        String str;
        d dVar = new d(25, TimeUnit.MINUTES, 8, d.a.a.a.SHA1);
        h.a.a.a.b.a aVar = new h.a.a.a.b.a();
        String infoGetTx = getInfoGetTx(2);
        Charset charset = h.a.a.a.a.f5326a;
        if (infoGetTx == null) {
            bArr = null;
        } else {
            bArr = infoGetTx.getBytes(charset);
        }
        if (!(bArr == null || bArr.length == 0)) {
            b.a aVar2 = new b.a();
            aVar.a(bArr, 0, bArr.length, aVar2);
            aVar.a(bArr, 0, -1, aVar2);
            int i = aVar2.f5337c;
            bArr = new byte[i];
            if (aVar2.f5336b != null) {
                int min = Math.min(i - aVar2.f5338d, i);
                System.arraycopy(aVar2.f5336b, aVar2.f5338d, bArr, 0, min);
                int i2 = aVar2.f5338d + min;
                aVar2.f5338d = i2;
                if (i2 >= aVar2.f5337c) {
                    aVar2.f5336b = null;
                }
            }
        }
        if (bArr != null) {
            c cVar = new c(bArr, dVar);
            Date date = new Date(System.currentTimeMillis());
            long j = 0;
            if (dVar.f4936c != 0) {
                j = date.getTime() / TimeUnit.MILLISECONDS.convert(dVar.f4936c, dVar.f4937d);
            }
            if (cVar.f4935b.f4932a <= 0) {
                str = "";
            } else {
                ByteBuffer putLong = ByteBuffer.allocate(8).putLong(0, j);
                g.a((Object) putLong, "ByteBuffer.allocate(8).putLong(0, counter)");
                Mac instance = Mac.getInstance(cVar.f4935b.f4933b.f4931b);
                instance.init(new SecretKeySpec(cVar.f4934a, "RAW"));
                byte[] doFinal = instance.doFinal(putLong.array());
                g.a((Object) doFinal, "hash");
                if (!(doFinal.length == 0)) {
                    byte b2 = (byte) (doFinal[doFinal.length - 1] & 15);
                    ByteBuffer allocate = ByteBuffer.allocate(4);
                    for (int i3 = 0; i3 <= 3; i3++) {
                        allocate.put(i3, doFinal[i3 + b2]);
                    }
                    allocate.put(0, (byte) (allocate.get(0) & Byte.MAX_VALUE));
                    g.a((Object) allocate, "binary");
                    String valueOf = String.valueOf(allocate.getInt() % ((int) Math.pow(10.0d, (double) cVar.f4935b.f4932a)));
                    int length = cVar.f4935b.f4932a - valueOf.length();
                    for (int i4 = 0; i4 < length; i4++) {
                        StringBuilder sb = new StringBuilder();
                        sb.append('0');
                        sb.append(valueOf);
                        valueOf = sb.toString();
                    }
                    str = valueOf;
                } else {
                    throw new NoSuchElementException("Array is empty.");
                }
            }
            return new a(Integer.parseInt(str) & 4095);
        }
        g.a("secret");
        throw null;
    }

    public static native String getInfoGetTx(int i);
}
