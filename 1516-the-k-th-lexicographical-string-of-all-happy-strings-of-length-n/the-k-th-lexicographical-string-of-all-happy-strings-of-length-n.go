func getHappyString(n int, k int) string {
	var result string
	count := 0

	var dfs func(string)

	dfs = func(curr string) {
		if result != "" {
			return
		}

		if len(curr) == n {
			count++
			if count == k {
				result = curr
			}
			return
		}

		for _, ch := range []byte{'a', 'b', 'c'} {
			if len(curr) == 0 || curr[len(curr)-1] != ch {
				dfs(curr + string(ch))
			}
		}
	}

	dfs("")
	return result
}