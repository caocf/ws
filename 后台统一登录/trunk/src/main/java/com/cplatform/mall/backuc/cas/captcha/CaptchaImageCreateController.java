package com.cplatform.mall.backuc.cas.captcha;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.BackgroundProducer;
import nl.captcha.backgrounds.SquigglesBackgroundProducer;
import nl.captcha.text.producer.NumbersAnswerProducer;
import nl.captcha.text.producer.TextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;
import nl.captcha.text.renderer.WordRenderer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CaptchaImageCreateController implements Controller, InitializingBean {

    protected final Log logger = LogFactory.getLog(getClass());

    private int width = 100;
    private int height = 25;

    private List<Color> colors = new ArrayList(2);
    private List<Font> fonts = new ArrayList(3);


    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        WordRenderer wordRenderer = new DefaultWordRenderer(colors, fonts);
        TextProducer textProducer = new NumbersAnswerProducer(4);
        BackgroundProducer backgroundProducer = new SquigglesBackgroundProducer();

        Captcha captcha = new Captcha.Builder(100, 25)
                //.addBackground(backgroundProducer)
                .addText(textProducer, wordRenderer)
                        //.gimp()
                        //.addBorder()
                .addNoise()
                .build();

        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0L);
        response.setContentType("image/png");
        ServletOutputStream responseOutputStream = response.getOutputStream();


        try
        {
            ImageIO.write(captcha.getImage(), "png", responseOutputStream);

        } catch (IOException e) {
            logger.warn("write captcha image error.", e);
        }

        request.getSession().setAttribute(Captcha.NAME, captcha);
        return null;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public List<Font> getFonts() {
        return fonts;
    }

    public void setFonts(List<Font> fonts) {
        this.fonts = fonts;
    }

    public void afterPropertiesSet() throws Exception {
        if (colors.isEmpty()) {
            colors.add(Color.BLACK);
            // colors.add(Color.BLUE);
        }
        if (fonts.isEmpty()) {
            fonts.add(new Font("Geneva", 2, 25));
            fonts.add(new Font("Courier", 1, 25));
            fonts.add(new Font("Arial", 1, 25));
        }
    }
}