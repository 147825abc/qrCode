package com.sky.generate.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class QRCodeService {
    public String generateQRCodeBase64(String content,int size) throws Exception{
        Map<EncodeHintType,Object> hints=new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION,"H");
        hints.put(EncodeHintType.MARGIN,1);

        BitMatrix matrix=new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,size,size,hints);
        try(ByteArrayOutputStream out=new ByteArrayOutputStream()){
            MatrixToImageWriter.writeToStream(matrix,"PNG",out);
            return Base64.getEncoder().encodeToString(out.toByteArray());
        }
    }
}
