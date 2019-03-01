package com.katonarobert.webpontbackend.util;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.krysalis.barcode4j.BarcodeException;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

public class EAN8Barcode {

    /**
     * @param args
     * @throws BarcodeException
     * @throws IOException
     */

    public static Configuration buildCfg(String type) {
        DefaultConfiguration cfg = new DefaultConfiguration("barcode");

        //Bar code type
        DefaultConfiguration child = new DefaultConfiguration(type);
        cfg.addChild(child);

        //Human readable text position
        DefaultConfiguration attr = new DefaultConfiguration("human-readable");
        DefaultConfiguration subAttr = new DefaultConfiguration("placement");
        subAttr.setValue("bottom");
        attr.addChild(subAttr);

        child.addChild(attr);
        return cfg;
    }
}