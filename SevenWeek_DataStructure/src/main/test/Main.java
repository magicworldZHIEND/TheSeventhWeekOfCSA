package main.test;

/**
 * @Classname : Main //类名
 * @Description: //描述
 * @Author : Administrator //作者
 * @Date : 2021/12/21 20:58//日期
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println("<---------第一题--------->");
        int nums1[] = {4, 1, 2};
        int nums2[] = {1, 3, 4, 2};
        int nums3[] = {4, 4, 4, 4, 4, 4, 4, 4};
        int nums4[] = {1, 2, 3, 4};
        pickTheNextBigger(nums1, nums2);
        pickTheNextBigger(nums3, nums4);
        System.out.println("<---------第二题--------->");
        int pushed1[] = {1, 2, 3, 4, 5};
        int popped1[] = {4, 5, 3, 2, 1};
        whetherEmptyStack(pushed1, popped1);
        int pushed2[] = {1, 2, 3, 4, 5};
        int popped2[] = {4, 3, 5, 1, 2};
        whetherEmptyStack(pushed2, popped2);
        System.out.println("<---------第三题--------->");
        int numbers1[] = {1, 2, 3, 2};
        int numbers2[] = {1, 1, 1, 1, 1};
        int numbers3[] = {1, 2, 3, 4, 5};
        int numbers4[] = {0, 0, 0, 0};
        System.out.println("唯一元素为:" + hashTable(numbers1) + "和为" + getSum(hashTable(numbers1)));
        System.out.println("唯一元素为:" + hashTable(numbers2) + "和为" + getSum(hashTable(numbers2)));
        System.out.println("唯一元素为:" + hashTable(numbers3) + "和为" + getSum(hashTable(numbers3)));
        System.out.println("唯一元素为:" + hashTable(numbers4) + "和为" + getSum(hashTable(numbers4)));

    }

    //问题:两个没有重复元素的数组,找出在nums1中任意元素x在nums2中对应的位置右边第一个比x大的元素,如果没有更大的,返回-1
    //思路:先将nums2倒置入栈,当找到x在num2的位置后,停止入栈,这样栈中的元素就是x以及x右边的数
    //方法:1.stack.peek(),stack.push(),stack.pop(),stack.empty()
    //2.Arrays.toString()快速输出数组
    public static void pickTheNextBigger(int num1[], int num2[]) {
        int num;
        for (int m = 0; m < num1.length; m++) {
            num = num1[m];
            Stack stack = new Stack();
            for (int i = num2.length - 1; num2[i] != num; i--) {
                //倒置入栈,当num 与 num2[i]相等的时候停止入栈
                // 因为要查找右边的数中比num大的，即已经入栈的
                stack.push(num2[i]);
            }
            //empty():boolean 判断栈中元素是否等于0
            while (!stack.empty()) {
                //peek()获得栈顶的元素, 将其强制转换成int类型进行比较
                //这样进行的前提是确定是一个整形数组
                if ((int) stack.peek() > num) {
                    num1[m] = (int) stack.peek();
                    break;
                } else {
                    //否则对将其从栈中进行移除
                    stack.pop();
                }
            }
            if (stack.empty()) {
                num1[m] = -1;
            }
        }
        //一种快速输出数组的方法
        System.out.println(Arrays.toString(num1));
    }

    //问题:给定pushed和popped两个序列,每个序列中的值都不会重复,只有当他们可能是在最初空栈上进行的推入push和弹出pop操作序列的结果时返回true,
    //即push和pop每个元素只能对应操作一次且参与操作,总体组合后达到空栈即可
    //思路:先将pushed根据数组的顺序依次压入栈中,压入过程中检查栈顶,如果与popped数组序列中有相同的就弹出,全部压入后检查栈的情况
    //方法:1.stack.peek(),stack.push(),stack.pop(),stack.empty()
    public static void whetherEmptyStack(int pushed[], int popped[]) {
        Stack stack = new Stack();
        int j = 0;
        //i为操作数目,i的操作次数一定不大于 元素个数的两倍
        for (int i = 0; i < 2 * pushed.length; ++i) {
            //判断是否可以出栈
            if (!stack.empty()) {
                if ((int) stack.peek() == popped[j]) {
                    stack.pop();
                    if (j < pushed.length) {
                        j++;
                    }
                }
            }
            //入栈
            if (i < pushed.length) {
                stack.push(pushed[i]);
            }
        }
        if (stack.isEmpty()) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    //问题:给一个整数数组nums,数组中唯一元素是那些只出现恰好一次的元素,请你返回唯一元素的和
    //思路:根据根据每个数的数值 指定一个哈希表,她们的数值就是哈希表的编号,如果有相同的数目,那么对应位置就加1
    //方法:1.Arraylist.get()方法获取指定元素
    //     2. 哈希表思维
    public static ArrayList<Integer> hashTable(int[] nums) {
        //先找到nums的最大值,来确定哈希表的链表长度
        int max = nums[0];
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        //利用哈希表的思想建立数组
        int numbers[] = new int[max + 1];
        for (int i = 0; i < nums.length; ++i) {
            numbers[nums[i]]++;
        }
        //创建用来容纳唯一数的数组
        ArrayList<Integer> unique = new ArrayList<>();
        //m为unique的编号
        for (int i = 0; i < numbers.length; ++i) {
            //nums[i]==1说明只出现了一次
            if (numbers[i] == 1) {
                unique.add(i);
            }
        }
        return unique;
    }

    public static int getSum(ArrayList<Integer> nums) {
        int sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum = sum + nums.get(i);
        }
        return sum;
    }
}


