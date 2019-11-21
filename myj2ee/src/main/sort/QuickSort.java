package sort;

import hwp.ListNode;

import javax.xml.soap.Node;
import java.io.FileReader;
import java.util.Arrays;

/**
 * 快排
 * @author caichengcheng
 * date:2019-11-05
 */
public class QuickSort {
    /**
     * 基于数组的快排
     * @param array
     * @param l
     * @param r
     */
    public static void arrayQuickSort(int[] array,int l,int r){
        if(l >= r){
            return ;
        }
        int i = l, j=r;
        int tmp = array[i];
        while(i < j){
            //从右往左找到第一个大于tmp的位置
            while( i< j && tmp <= array[j]){
                j--;
            }
            //到这里，有两种情况，1是找到了第一个，2是右边没有一个数比array[i]小
            if(i < j){
                //这里if判断，是针对第一种情况，进行换位，将找到的第一个较小值，置换array[i]
                array[i++] = array[j];
            }

            while(i<j && array[i] < tmp){
                i++;
            }
            if(i < j){
                array[j--] = array[i];
            }
        }
        array[i] = tmp;
        arrayQuickSort(array,l,i-1);
        arrayQuickSort(array,i+1,r);
    }

    public static void main(String[] args) {
        int[] a = new int[]{8,1,3,7,4,6,5};
        ListNode listNode = new ListNode(a[0]);
        ListNode head = listNode;
        for(int i = 1;i<a.length;i++){
            ListNode node = new ListNode(a[i]);
            listNode.next = node;
            listNode = node;
        }

        arrayQuickSort(a,0,a.length-1);
        Arrays.stream(a).forEach(System.out::print);
        System.out.println();

        printNode(head);
        System.out.println();
        quickSort(head,listNode);
        printNode(head);

    }

    static void printNode(ListNode head){
        while(head!=null){
            System.out.print(head.val);
            head = head.next;
        }
    }

    /**
     * 基于单链表的快排
     * @param begin
     * @param end
     */
    static void quickSort(ListNode begin,ListNode end){
        if(begin == null || end == null || begin == end){
            return ;
        }
        ListNode first = begin,second = begin.next;
        int tmpValue = begin.val;
        //从前向后,将比tmpValue小的节点，都把值置换到first.next上，
        while(second != end.next && second !=null){
            if(second.val < tmpValue){
                first = first.next;
                if(first != second){
                    int t = first.val;
                    first.val = second.val;
                    second.val = t;
                }
            }
            second = second.next;
        }
        //到这里，first以及first之前的节点，value<tmp, 之后的节点，value>=tmp
        if(first != begin){
            int t  = first.val;
            first.val = begin.val;
            begin.val = t;
        }

        quickSort(begin,first);
        quickSort(first.next,end);

    }




}
