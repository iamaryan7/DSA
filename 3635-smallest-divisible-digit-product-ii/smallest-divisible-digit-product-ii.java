class Solution {

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public String smallestNumber(String num, long t) {

        long tmp = t;

        for (int p : new int[]{2, 3, 5, 7}) {
            while (tmp % p == 0) {
                tmp /= p;
            }
        }

        if (tmp != 1) {
            return "-1";
        }

        int n = num.length();

        long[] rem = new long[n + 1];
        rem[0] = t;

        int firstZero = n;

        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';

            if (d == 0 && firstZero == n) {
                firstZero = i;
            }

            rem[i + 1] = rem[i] / gcd(rem[i], d == 0 ? 1 : d);
        }

        if (firstZero == n && rem[n] == 1) {
            return num;
        }

        char[] arr = num.toCharArray();

        int start = (firstZero == n) ? n - 1 : firstZero;

        for (int i = start; i >= 0; i--) {

            int current = arr[i] - '0';

            long needBefore = rem[i];

            int begin = Math.max(current + 1, 1);

            if (i >= firstZero) {
                begin = 1;
            }

            for (int dig = begin; dig <= 9; dig++) {

                long need = needBefore / gcd(needBefore, dig);

                char[] candidate = arr.clone();
                candidate[i] = (char) ('0' + dig);

                long curNeed = need;

                for (int j = n - 1; j > i; j--) {

                    int chosen = 1;

                    for (int d = 9; d >= 1; d--) {
                        if (curNeed % d == 0) {
                            chosen = d;
                            curNeed /= d;
                            break;
                        }
                    }

                    candidate[j] = (char) ('0' + chosen);
                }

                if (curNeed == 1) {

                    for (int j = i + 1; j < n; j++) {
                        if (candidate[j] == 0) {
                            candidate[j] = '1';
                        }
                    }

                    String ans = new String(candidate);

                    boolean zeroFree = true;
                    for (int k = 0; k < ans.length(); k++) {
                        if (ans.charAt(k) == '0') {
                            zeroFree = false;
                            break;
                        }
                    }

                    if (zeroFree && ans.compareTo(num) >= 0) {
                        return ans;
                    }
                }
            }
        }

        StringBuilder digits = new StringBuilder();

        long need = t;

        for (int d = 9; d >= 2; d--) {
            while (need % d == 0) {
                digits.append((char) ('0' + d));
                need /= d;
            }
        }

        if (need != 1) {
            return "-1";
        }

        while (digits.length() < n + 1) {
            digits.append('1');
        }

        char[] res = digits.toString().toCharArray();

        java.util.Arrays.sort(res);

        return new String(res);
    }
}