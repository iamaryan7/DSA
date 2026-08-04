class Solution {
public:
    vector<int> findMissingElements(vector<int>& nums) {
        int mn = *min_element(nums.begin(), nums.end());
        int mx = *max_element(nums.begin(), nums.end());

        unordered_set<int> s(nums.begin(), nums.end());
        vector<int> ans;

        for (int i = mn; i <= mx; i++) {
            if (!s.count(i)) {
                ans.push_back(i);
            }
        }

        return ans;
    }
};