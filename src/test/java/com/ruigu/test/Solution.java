package com.ruigu.test;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static List<Integer> partitionLabels(String S) {
        List<Integer> list = new ArrayList<>();
        int i = 0;

        while(i<S.length()){
            int start = i;
            int end = i+1;
            while(i<end){
                int j = -1;
                char ch = S.charAt(i);
                if((j =S.lastIndexOf(ch,S.length())) !=-1){
                    if(j>=end){
                        end = j+1;
                    }
                }
                i++;
            }
            list.add(end-start);
        }

        return list;

    }

    public static boolean isValid(String s) {

        while(s.length() >0){
            if(s.indexOf("()") == -1 && s.indexOf("[]") == -1 && s.indexOf("{}") == -1){
                break;
            }
            s = s.replace("()","");
            s = s.replace("[]","");
            s = s.replace("{}","");
        }

        if(s.length() >0){
            return false;
        }
        return true;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node1 = l1;
        ListNode node2 = l2;

        ListNode head = null;
        ListNode currentNode = null;

        while(node1 != null || node2 != null){

            if(node1 != null && node2 != null){
                int val1 = node1.val;
                int val2 = node2.val;

                if(val1 < val2){
                    ListNode newNode = new ListNode(val1);
                    if(head == null){
                        currentNode = head =newNode;
                    }else{
                        currentNode.next = newNode;
                        currentNode = newNode;

                    }
                    node1 = node1.next;
                }else if(val1 > val2){
                    ListNode newNode = new ListNode(val2);
                    if(head == null){
                        currentNode = head =newNode;
                    }else{
                        currentNode.next = newNode;
                        currentNode = newNode;

                    }
                    node2 = node2.next;
                }else{
                    ListNode newNode1 = new ListNode(val1);
                    ListNode newNode2 = new ListNode(val2);
                    if(head == null){
                        currentNode = head =newNode1;
                        newNode1.next = newNode2;
                        currentNode = newNode2;
                    }else{
                        currentNode.next = newNode1;
                        newNode1.next = newNode2;
                        currentNode = newNode2;
                    }
                    node1 = node1.next;
                    node2 = node2.next;
                }

            }else if(node1 != null){
                ListNode newNode = new ListNode(node1.val);
                if(head == null){
                    currentNode = head =newNode;
                }else{
                    currentNode.next = newNode;
                    currentNode = newNode;

                }
                node1 = node1.next;
            }else {
                ListNode newNode = new ListNode(node2.val);
                if(head == null){
                    currentNode = head =newNode;
                }else{
                    currentNode.next = newNode;
                    currentNode = newNode;

                }
                node2 = node2.next;
            }

        }

        return head;
    }

    public static void main(String[] args) {
       /* List<Integer> list = partitionLabels("ababcbacadefegdehijhklij");
        System.out.println(list);*/
        //System.out.println(isValid("(([]){})"));
        int A[] = {1,2,3,0,0,0};
        int B[] = {2,5,6};
        merge(A,3,B,3);
    }


    public static void merge(int[] A, int m, int[] B, int n) {

        for(int i=0;i<n;i++){
            boolean flag = false;
            for(int j=0;j<m;j++){
                if(B[i]<A[j]){
                    for(int k=m-1;k>=j;k--){
                        if(k+1 == 6){
                            System.out.println();
                        }
                        A[k+1] = A[k];
                    }
                    A[j] = B[i];
                    flag = true;
                    m++;
                    break;
                }
            }

            if(!flag){
                A[m++] = B[i];
            }
        }

    }


  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

}
