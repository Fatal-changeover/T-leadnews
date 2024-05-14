import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * ClassName: test
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author R
 * @Create 2024/5/14 23:05
 * @Version 1.0
 */
public class test {
    public static void main(String[] args) {
        File file = new File("D:\\Z_XIANGMU\\1.png");
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("D:\\Z_XIANGMU\\tessdata");
        tesseract.setLanguage("chi_sim");
        //执行ocr识别
        String result = null;
        try {
            result = tesseract.doOCR(file);
            //替换回车和tal键  使结果为一行
            result = result.replaceAll("\\r|\\n","-").replaceAll(" ","");
            System.out.println("识别的结果为："+result);
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }

    }


}
