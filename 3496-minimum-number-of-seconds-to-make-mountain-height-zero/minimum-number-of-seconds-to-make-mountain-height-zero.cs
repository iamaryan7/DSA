public class Solution
{
    public long MinNumberOfSeconds(int mountainHeight, int[] workerTimes)
    {
        bool CanFinish(long time)
        {
            long totalHeight = 0;

            foreach (int wt in workerTimes)
            {
                // t * x * (x + 1) / 2 <= time
                long x = (long)((Math.Sqrt(1.0 + 8.0 * time / wt) - 1.0) / 2.0);

                totalHeight += x;

                if (totalHeight >= mountainHeight)
                    return true;
            }

            return false;
        }

        long left = 0;
        long minWorker = workerTimes.Min();

        long right = minWorker * (long)mountainHeight * (mountainHeight + 1) / 2;

        while (left < right)
        {
            long mid = left + (right - left) / 2;

            if (CanFinish(mid))
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }
}