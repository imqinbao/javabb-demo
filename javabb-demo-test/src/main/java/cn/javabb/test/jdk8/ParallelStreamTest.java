package cn.javabb.test.jdk8;

import cn.javabb.test.model.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/09/02 22:52
 */
public class ParallelStreamTest {
    /**
     * Stream 顺序处理流
     * ParallelStream 并行处理流
     */

    @Test
    public void streamVs() {
        List<Integer> list1 = new ArrayList<>(10000);
        List<Integer> list2 = new ArrayList<>(10000);
        List<Integer> list3 = new ArrayList<>(10000);

        List<Integer> list22 = Collections.synchronizedList(new ArrayList<>(10000));
        Lock lock = new ReentrantLock();

        IntStream.range(0, 10000).forEach(list1::add);

        IntStream.range(0, 10000).parallel().forEach(list2::add);

        IntStream.range(0, 10000).parallel().forEach(list22::add);

        IntStream.range(0, 10000).parallel().forEach(i -> {
            lock.lock();
            try {
                list3.add(i);
            } finally {
                lock.unlock();
            }
        });

        System.out.println("串行执行的大小：" + list1.size());
        System.out.println("并行执行的大小：" + list2.size());
        System.out.println("线程安全-并行执行的大小：" + list22.size());
        System.out.println("加锁并行执行的大小：" + list3.size());
    }

    @Test
    public void streamVs2() {
        List<Person> persons = constructPersons();
        doFor(persons);
        doStream(persons);
        doParallelStream(persons);
    }

    /**
     * 构造数据
     *
     * @return
     */
    public List<Person> constructPersons() {

        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 5; i++) {
            Person p = new Person(i, "name" + i, "sex" + i, i);
            persons.add(p);
        }
        return persons;
    }

    /**
     * for
     *
     * @param persons
     */
    public void doFor(List<Person> persons) {
        long start = System.currentTimeMillis();

        for (Person p : persons) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            //System.out.println(p.name);
        }

        long end = System.currentTimeMillis();
        System.out.println("doFor cost:" + (end - start));
    }

    /**
     * 顺序流
     *
     * @param persons
     */
    public void doStream(List<Person> persons) {
        long start = System.currentTimeMillis();

        persons.stream().forEach(x -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            //System.out.println(x.name);
        });

        long end = System.currentTimeMillis();
        System.out.println("doStream cost:" + (end - start));
    }

    /**
     * 并行流
     *
     * @param persons
     */
    public void doParallelStream(List<Person> persons) {

        long start = System.currentTimeMillis();

        persons.parallelStream().forEach(x -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            //System.out.println(x.name);
        });

        long end = System.currentTimeMillis();

        System.out.println("doParallelStream cost:" + (end - start));
    }

}
