/*  класс Inspector –  инспектор, который следит за запрещенными и украденными посылками и бьет тревогу в виде
исключения, если была обнаружена подобная посылка. Если он заметил запрещенную посылку с одним из запрещенных
содержимым ("weapons" и "banned substance"), то он бросает IllegalPackageException. Если он находит посылку,
состоящую из камней (содержит слово "stones"), то тревога прозвучит в виде StolenPackageException.
Оба исключения вы должны объявить самостоятельно в виде непроверяемых исключений /
 */

public class Inspector implements MailService {
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage) { // с помощью оператора instanceof определяем является объект Sendable  посылкой и если является
            Package s = ((MailPackage) mail).getContent() ; // то создаём посылку идентичную mail
            String contentPackage = s.getContent(); // с помощью метода getContent получаем содержимое посылки
            if (contentPackage.contains(WEAPONS) || contentPackage.contains(BANNED_SUBSTANCE)) { //если в содержимом есть "weapons" или "banned substance"
                throw new IllegalPackageException(); //то бросаем исключение IllegalPackageException
            } else if (contentPackage.contains("stones")) { //если содержимое посылки состоит из камней
                throw new StolenPackageException(); //то бросаем исключение StolenPackageException
            }
        }
        return mail;
    }
}



