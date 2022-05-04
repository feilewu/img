package github.resources.img.manager.writer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RunWith(JUnit4.class)
public class LocalWriterTest {


    @Test
    public void testSeparateImgUri() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        String uri = "2021"+ File.separator+"08"+File.separator+"21"+File.separator+"people.png";
        Class<?> clazz = Class.forName("github.resources.img.manager.writer.LocalWriter");
        Method getImageName = clazz.getDeclaredMethod("getImageName",String.class);
        getImageName.setAccessible(true);
        String imageName = (String) getImageName.invoke(clazz.newInstance(), uri);
        Assert.assertEquals("people",imageName);

        Method getSuffix = clazz.getDeclaredMethod("getSuffix",String.class);
        getSuffix.setAccessible(true);
        String suffix = (String) getSuffix.invoke(clazz.newInstance(), uri);
        Assert.assertEquals("png",suffix);

        Method getPrefix = clazz.getDeclaredMethod("getPrefix",String.class,String.class);
        getPrefix.setAccessible(true);
        String prefix = (String) getPrefix.invoke(clazz.newInstance(), uri,null);
        Assert.assertEquals("2021/08/21",prefix);
    }



}
