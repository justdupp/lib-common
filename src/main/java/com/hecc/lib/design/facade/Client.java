package com.hecc.lib.design.faced;

/**
 * @author xuhoujun
 * @description:
 * @date: created In 2:48 PM on 2019/8/26.
 */
public class Client {

    public static void main(String[] args) {

        ModenPostOffice modenPostOffice = new ModenPostOffice();
        
        String context = "33333";
        String address = "4444";
        modenPostOffice.sendLetter(context,address);
   /*     ILetterProcess iLetterProcess = new LetterProcessImpl();
        iLetterProcess.writeContent("111");
        iLetterProcess.fillEnvelope("222");
        iLetterProcess.letterIntoEnvelope();
        iLetterProcess.sendLetter();*/
    }
}
