/* Spy – шпион, который логгирует о всей почтовой переписке, которая проходит через его руки.
Объект конструируется от экземпляра Logger, с помощью которого шпион будет сообщать о всех действиях.
 Он следит только за объектами класса MailMessage и пишет в логгер следующие сообщения (в выражениях нужно
 заменить части в фигурных скобках на значения полей почты):
2.1) Если в качестве отправителя или получателя указан "Austin Powers", то нужно написать в лог сообщение
с уровнем WARN: Detected target mail correspondence: from {from} to {to} "{message}"
2.2) Иначе, необходимо написать в лог сообщение с уровнем INFO: Usual correspondence: from {from} to {to}*/

import java.util.logging.Logger;

public class Spy implements MailService {
    private final Logger spyLoger; //создаем логер
    public static final String AUSTIN_POWERS = "Austin Powers";

    public Spy(Logger spyLoger) {
        this.spyLoger = spyLoger;}
    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailMessage) { //с помощью оператора instanceof определяем является объект Sendable  письмом и если является
            MailMessage mes = (MailMessage) mail; //то  создаем письмо идентичное mail
            String from = mes.getFrom(); //отправитель письма
            String to = mes.getTo(); //получатель письма
            if (from.equals(AUSTIN_POWERS) || to.equals(AUSTIN_POWERS)) { //если в качестве отправителя или получателя указан "Austin Powers"
                //с помощью метода getMessage получаем текст письма
                spyLoger.warning("Detected target mail correspondence: from " + from + " to " + to + " \"" + mes.getMessage() + "\"");
                } else {
                spyLoger.info("Usual correspondence: from " + from + " to " + to + "");
                }
            }
            return mail; // возвращаем оригинальное письмо
        }
    }