package Emotions;

import io.indico.Indico;
import io.indico.api.image.FacialEmotion;
import io.indico.api.utils.IndicoException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Kamil on 20.04.2018.
 */
public class IndicoApi {

    private Indico indico;

    public IndicoApi() {

        try {
            indico = new Indico("40d8aa60cc93f49d6c95585f73abdfcd");
        } catch (IndicoException e) {
            e.printStackTrace();
        }
    }

    public void detect(){
        Map<FacialEmotion, Double> result = null;
        try {
            result = indico.fer.predict("./test.jpg").getFer();
        } catch (IndicoException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
