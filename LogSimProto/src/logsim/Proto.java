package logsim;

import java.io.File;
import logsim.model.Circuit;
import logsim.model.Value;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Toggle;

public class Proto implements Controllable {
    Viewable view;
    Circuit c;

    public Proto() {
        // tegy�k fel, hogy a felhaszn�l� tol egy loadCircuit()-ot.
//        Circuit c = new Parser().parse(new File("test.txt"));
//        c.evaluate();
//        Led led1 = (Led) c.getComponentByName("led1");
//        Led led2 = (Led) c.getComponentByName("led2");
//        System.out.println("led1: " + led1.getValue());
//        System.out.println("led2: " + led2.getValue());
    view = new ConsoleView(this);
    view.Run();

    }



    public static void main(String[] args) {
        new Proto();
    }

    /* 
     * Parancs �rtelmez�se
     */
    @Override
    public void Eval(String s) {
        String cmds[] = s.split(" ");
        if(cmds[0].equals("loadCircuit")){
           c = new Parser().parse(new File(cmds[1]));
        } else if(cmds[0].equals("loadSettings"))
        {
            //Attila f�ggv�ny�nek h�v�s
        } else if(cmds[0].equals("saveSetting")){
            //Attila f�ggv�ny�nek h�v�sa
        } else if(cmds[0].equals("switch")){
            Toggle sw = (Toggle) c.getComponentByName(cmds[1]);
            Value[] v = sw.getValues();
            v[0] = v[0].invert();
            sw.setValues(v);
        } else if(cmds[0].equals("setSeqGen")){
//            SequenceGenerator sg = (SequenceGenerator)c.getComponentByName(cmd[1]);
//            sg.setValues(cmds[2]);
        } else if(cmds[0].equals("check")){
            if(cmds[1].equals("-all"))
            {
                //�sszes elem kilist�z�sa

            }else {
                view.WriteDetails(c.getComponentByName(cmds[1]));
            }
        } else if(cmds[0].equals("step")){
            c.evaluate();
        }



    }
}
