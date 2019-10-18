package com.hecc.lib.design.faced;

/**
 * @author xuhoujun
 * @description:
 * @date: created In 2:43 PM on 2019/8/26.
 */
public interface ILetterProcess {

    void writeContent(String content);

    void fillEnvelope(String address);

    void letterIntoEnvelope();

    void sendLetter();
}
