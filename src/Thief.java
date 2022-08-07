/* Thief – класс вор, который ворует самые ценные посылки и игнорирует все остальное.
Вор принимает в конструкторе переменную int – минимальную стоимость посылки, которую он будет воровать.
Также, в данном классе должен присутствовать метод getStolenValue, который возвращает суммарную стоимость
всех посылок, которые он своровал. Воровство происходит следующим образом: вместо посылки, которая пришла вору,
он отдает новую, такую же, только с нулевой ценностью и содержимым посылки "stones instead of {content}"*/

public class Thief implements MailService {
    private int minShippingPackage; //минимальная стоимость посылки, которую будет воровать
    private int sumStolen = 0; // поле для суммарной стоимости всех сворованных посылок

    public Thief(int minShippingPackage) {
        this.minShippingPackage = minShippingPackage;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage) { // с помощью оператора instanceof определяем является объект Sendable  посылкой и если является
            Package p = ((MailPackage) mail).getContent() ; // то создаем посылку идентичную mail
            int packagePrice = p.getPrice(); // получаем  ценность посылки
            if (minShippingPackage <= packagePrice) { //если минимальная стоимость меньше либо равна ценности посылки
                Package newPackage = new Package("stones instead of " + p.getContent(), 0); //создаем новую посылку
                sumStolen += packagePrice; //суммируем стоимость украденных посылок
                return new MailPackage(mail.getFrom(), mail.getTo(), newPackage);
            }
        }
        return mail; // возвращаем оригинальное письмо
    }

//метод getStolenValue возвращает суммарную стоимость всех посылок
    public int getStolenValue() {
        return sumStolen;
    }
}




