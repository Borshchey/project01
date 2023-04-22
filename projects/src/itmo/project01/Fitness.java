package itmo.project01;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Fitness {
    Subscription[] subscriptionsWithSwimmingPool = new Subscription[20];
    int count01 = 0;
    Subscription[] subscriptionsWithGym = new Subscription[20];
    int count02 = 0;
    Subscription[] subscriptionsWithGroupClasses = new Subscription[20];
    int count03 = 0;


    public void closeFitness() {
            for (int i = 0; i < 20; i++) {
                if (this.subscriptionsWithSwimmingPool[i] != null) this.subscriptionsWithSwimmingPool[i] = null;
                if (this.subscriptionsWithGym[i] != null) this.subscriptionsWithGym[i] = null;
                if (this.subscriptionsWithGroupClasses[i] != null) this.subscriptionsWithGroupClasses[i] = null;
            }
    }

    public void addPersonToZone(Zone zone, Subscription subscription) {
        if (LocalDate.now().isAfter(subscription.getExpirationDate())) {
            System.out.println("Проход запрещен - закончился срок действия абонемента");
            return;
        }

        if  (zone.equals(Zone.GROUPCLASSES) && subscription.getType().isGroupClasses() == false) {
            System.out.println("Проход запрещен  - для этого абонемента запрещено посещение этой зоны");
            return;
        }

        if  (zone.equals(Zone.SWIMMINGPOOL) && subscription.getType().isSwimmingPool() == false) {
            System.out.println("Проход запрещен  - для этого абонемента запрещено посещение этой зоны");
            return;
        }
        if (zone.equals(Zone.SWIMMINGPOOL) && subscriptionsWithSwimmingPool.length == 20 ) {
            System.out.println("Проход запрещен - закончилось место в запрашиваемой зоне");
            return;
        }

        if (zone.equals(Zone.GYM) && subscriptionsWithGym.length == 20 ) {
        System.out.println("Проход запрещен - закончилось место в запрашиваемой зоне");
        return;
        }

        if (zone.equals(Zone.GROUPCLASSES) && subscriptionsWithGroupClasses.length == 20 ) {
                System.out.println("Проход запрещен - закончилось место в запрашиваемой зоне");
                return;
                }

        if (zone.equals(Zone.GROUPCLASSES)) {addPersonToGC(subscription);}
        if (zone.equals(Zone.SWIMMINGPOOL)) {addPersonToSP(subscription);}
        if (zone.equals(Zone.GYM)) {addPersonToGym(subscription);}

    }

    enum Zone {GYM, SWIMMINGPOOL, GROUPCLASSES}

    private Subscription[] addPersonToSP(Subscription subscription) {
        if (count01 < subscriptionsWithSwimmingPool.length) {
            subscriptionsWithGym[count01] = subscription;
            count01++;
        } else {
            System.out.println("Бассейн зал переполнен");
        }
        return subscriptionsWithSwimmingPool;
    }

    private Subscription[] addPersonToGym(Subscription subscription) {
        if (count02 < subscriptionsWithGym.length) {
            subscriptionsWithGym[count02] = subscription;
            count02++;
        } else {
            System.out.println("Тренажерный зал переполнен");
        }
        return subscriptionsWithGym;
    }

    private Subscription[] addPersonToGC(Subscription subscription) {
        if (count03 < subscriptionsWithGroupClasses.length) {
            subscriptionsWithGym[count03] = subscription;
            count03++;
        } else {
            System.out.println("Групповые класы переполнены зал переполнен");
        }
        return subscriptionsWithGroupClasses;
    }

    public void getInfo(Subscription subscription) {
        String name = subscription.getPerson().getName();
        String surname = subscription.getPerson().getSurname();
        String zone = "";

        if (subscription.getType().isGroupClasses()) {
            zone = "групповые занятия";
        }
        if (subscription.getType().isGym()) {
            zone = "тренажерный зал";
        }
        if (subscription.getType().isSwimmingPool()) {
            zone = "бассейн";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println("Фамилия " + surname +
                " Имя " + name +
                "Посещаемая зона " + zone +
                "Дата и время посещения " + dateTime.format(formatter));
    }

    public void printPersonsList() {

        for (Subscription subscription : subscriptionsWithGym) {
            if (subscriptionsWithGym.length == 0) {
                System.out.println("В зале пусто");
            }
            getInfo(subscription);
        }

        for (Subscription subscription : subscriptionsWithSwimmingPool) {
            if (subscriptionsWithSwimmingPool.length == 0) {
                System.out.println("В бассейне пусто");
            }
            getInfo(subscription);
        }
        for (Subscription subscription : subscriptionsWithGroupClasses) {
            if (subscriptionsWithGroupClasses.length == 0) {
                System.out.println("В групповых классах пусто");
            }
            getInfo(subscription);
        }
    }
}
