
package cn.zkz.structure.sort;

/**
 * 排序算法
 */
public class OrderData {

    public static int[] maopao(int[] data) {
        int sum = 0;
        for (int i=0; i<data.length-1; i++) {
            for (int j=0; j<data.length-i-1; j++) {
                sum++;
                if (data[i] >data[j]) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
        for (int i=0; i<data.length; i++) {
            System.out.println(data[i]);
        }
        System.out.println("-------"+ sum);
        return data;
    }
    public static int[] maopaoPlus(int[] data) {
        int sum = 0;
        boolean flag = true;
        for (int i=0; i<data.length-1 && flag; i++) {
            flag = false;
            for (int j=i; j<data.length; j++) {
                sum++;
                if (data[i] >data[j]) {
                    flag = true;
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
        for (int i=0; i<data.length; i++) {
            System.out.println(data[i]);
        }
        System.out.println("-------"+ sum);
        return data;
    }

    /**
     * 快速排序
     * @param arr
     * @return
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 找寻基准数据的正确索引
            int index = getIndex(arr, low, high);

            // 进行迭代对index之前和之后的数组进行相同的操作使整个数组变成有序
            //quickSort(arr, 0, index - 1); 之前的版本，这种姿势有很大的性能问题，谢谢大家的建议
            quickSort(arr, low, index - 1);
            quickSort(arr, index + 1, high);
        }


        for (int k=0; k<arr.length; k++) {
            System.out.println(arr[k]);
        }
    }
    private static int getIndex(int[] arr, int low, int high) {
        // 基准数据
        int tmp = arr[low];
        while (low < high) {
            // 当队尾的元素大于等于基准数据时,向前挪动high指针
            while (low < high && arr[high] >= tmp) {
                high--;
            }
            // 如果队尾元素小于tmp了,需要将其赋值给low
            arr[low] = arr[high];
            // 当队首元素小于等于tmp时,向前挪动low指针
            while (low < high && arr[low] <= tmp) {
                low++;
            }
            // 当队首元素大于tmp时,需要将其赋值给high
            arr[high] = arr[low];

        }
        // 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
        // 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
        arr[low] = tmp;
        return low; // 返回tmp的正确位置
    }

    /**
     * 选择排序
     * @param data
     */
    public static void selectSort(int[] data) {

        for (int i=0; i<data.length-1; i++) {
            for (int j=i+1; j< data.length; j++) {
                if (data[i] > data[j]) {
                    int temp = data[i];
                    data[j] = data[i];
                    data[i] = temp;
                }
            }
        }
        for (int k=0; k<data.length; k++) {
            System.out.print(data[k] + ",");
        }

    }
    public static void main(String[] args) {
        int[] data = new int[]{1,21,4,5,7,15,1,55,43,12,335,23,1};
        maopao(data);
        maopaoPlus(data);
        quickSort(data, 0, data.length-1);
        selectSort(data);
    }
}
