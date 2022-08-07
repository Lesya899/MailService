
/* UntrustworthyMailWorker – класс, моделирующий ненадежного работника почты, который вместо того, чтобы передать почтовый объект непосредственно в сервис почты, последовательно передает этот объект набору третьих лиц,
 а затем, в конце концов, передает получившийся объект непосредственно экземпляру RealMailService. У UntrustworthyMailWorker должен быть конструктор от массива MailService ( результат вызова processMail первого элемента
  массива передается на вход processMail второго элемента, и т. д.) и метод getRealMailService, который возвращает ссылку на внутренний экземпляр RealMailService */

public  class UntrustworthyMailWorker implements MailService {
    private MailService real = new RealMailService(); //создаем экземпляр RealMailService
    private MailService[] thirdParties; //массив третьих лиц

    public UntrustworthyMailWorker(MailService[] thirdParties) {

        this.thirdParties = thirdParties;
    }

    //метод getRealMailService возвращает ссылку на внутренний экземпляр RealMailService
    public MailService getRealMailService() {

        return real;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        for (int i = 0; i < thirdParties.length; i++) {
            mail = thirdParties[i].processMail(mail); //здесь получаем объект, обработанный очередным элементом массива т.е третьим лицом

        }
        return getRealMailService().processMail(mail); // объект, обработанный экземпляром RealMailService

    }
}

