package itmo.project01;

import java.time.LocalDate;

public class Application {

    public static void main(String[] args) {
        Fitness fitness01 = new Fitness();
        Person person01 = new Person("Jho", "Dow", 1990);

        Subscription subscription01 = new Subscription(1, person01, Subscription.SubscriptionType.DAY_SUBSCRIPTION);
        subscription01.setExpirationDate(subscription01.getRegisteredDate(), subscription01.getType());
        fitness01.addPersonToZone(Fitness.Zone.SWIMMINGPOOL, subscription01);
        fitness01.printPersonsList();
        fitness01.closeFitness();
    }
}
