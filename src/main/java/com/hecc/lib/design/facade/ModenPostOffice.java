package com.hecc.lib.design.faced;

/**
 * @author xuhoujun
 * @description:
 * @date: created In 2:56 PM on 2019/8/26.
 */
public class ModenPostOffice {

    private ILetterProcess letterProcess = new LetterProcessImpl();
    private Police police = new Police();

    public void sendLetter(String context,String address){
        letterProcess.writeContent(context);
        letterProcess.fillEnvelope(address);
        police.checkLetter(letterProcess);
        letterProcess.letterIntoEnvelope();
        letterProcess.sendLetter();
    }
}
