package itmo.project01;

import java.time.LocalDate;
import java.time.LocalTime;

public class Subscription{

    private int number;
    private LocalDate registeredDate = LocalDate.now();
    private LocalDate expirationDate;
    private Person person;
    private SubscriptionType type;

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public SubscriptionType getType() {
        return type;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setExpirationDate(LocalDate registeredDate, SubscriptionType type) {
    if (type == SubscriptionType.DAY_SUBSCRIPTION) { this.expirationDate = registeredDate.plusDays(1); return;}
    if (type == SubscriptionType.DISPOSABLE_SUBSCRIPTION) { this.expirationDate = registeredDate.plusDays(30);}
    else { this.expirationDate = registeredDate.plusDays(60);}
    }

    public Subscription(int number, Person person, SubscriptionType type) {
        this.number = number;
        this.person = person;
        this.type = type;

    }
    enum SubscriptionType {
        DAY_SUBSCRIPTION(LocalTime.of(8,00),
                LocalTime.of(22,00),
                true, true, false) ,
        DISPOSABLE_SUBSCRIPTION(LocalTime.of(8,00),
                LocalTime.of(16 ,00),
                true, false, true),
        FULL_SUBSCRIPTION(LocalTime.of(8,00),
                LocalTime.of(22,00),
                true, true, true);

        private LocalTime startTime ;
        private LocalTime endTime;
        private boolean gym;
        private boolean swimmingPool;
        private boolean groupClasses;

        SubscriptionType(LocalTime startTime, LocalTime endTime, boolean gym, boolean swimmingPool, boolean groupClasses) {

            this.startTime = startTime;
            this.endTime = endTime;
            this.gym = gym;
            this.swimmingPool = swimmingPool;
            this.groupClasses = groupClasses;
        }

        public boolean isGym() {
            return gym;
        }

        public boolean isSwimmingPool() {
            return swimmingPool;
        }

        public boolean isGroupClasses() {
            return groupClasses;
        }
    }
}
