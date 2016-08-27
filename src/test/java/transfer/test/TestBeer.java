package transfer.test;

/**
 * Created by Jake on 7/10 0010.
 */
public class TestBeer {

    private static int PRICE_BOTTLE = 2;

    private static int PRICE_COVER = 4;

    private static int PRICE_ALL = 2;

    private Counter bottles = new Counter();

    private Counter covers = new Counter();

    private int curBottle = 0;


    public TestBeer(int money) {
        int buy = money / PRICE_ALL;
        bottles.add(buy);
        covers.add(buy);
        this.curBottle = buy;
    }

    public void doConsumer() {
        boolean hasChange;
        do {
            hasChange = false;

            // 用盖子交换
            if (exchangeWith(covers, PRICE_COVER)) {
                hasChange = true;

            }

            // 用瓶子交换
            if (exchangeWith(bottles, PRICE_BOTTLE)) {
                hasChange = true;
            }

        } while (hasChange);

        System.out.println(this.curBottle);
    }

    private boolean exchangeWith(Counter container, int price) {
        boolean hasChange = false;
        while (container.get() >= price) {
            int exchangeWithCover = container.get() / price;
            int remainCover = container.get() % price;

            //使用资源
            int useCover = container.get() - remainCover;
            container.reduce(useCover);


            // 交换得到资源
            this.curBottle += exchangeWithCover;
            covers.add(exchangeWithCover);
            bottles.add(exchangeWithCover);
            hasChange = true;
        }
        return hasChange;
    }

    static class Counter {

        private int count;

        public void add(int val) {
            this.count += val;
        }

        public void reduce(int val) {
            this.count -= val;
        }

        public int get() {
            return this.count;
        }

    }

    public static void main(String[] args) {
        new TestBeer(10).doConsumer();
    }

}


