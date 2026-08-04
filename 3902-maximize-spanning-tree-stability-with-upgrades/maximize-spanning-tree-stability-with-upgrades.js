class DSU {
    constructor(n) {
        this.parent = Array.from({ length: n }, (_, i) => i);
        this.rank = new Array(n).fill(0);
        this.components = n;
    }

    find(x) {
        if (this.parent[x] !== x) {
            this.parent[x] = this.find(this.parent[x]);
        }
        return this.parent[x];
    }

    union(a, b) {
        let pa = this.find(a);
        let pb = this.find(b);

        if (pa === pb) return false;

        if (this.rank[pa] < this.rank[pb]) {
            [pa, pb] = [pb, pa];
        }

        this.parent[pb] = pa;

        if (this.rank[pa] === this.rank[pb]) {
            this.rank[pa]++;
        }

        this.components--;
        return true;
    }
}

/**
 * @param {number} n
 * @param {number[][]} edges
 * @param {number} k
 * @return {number}
 */
var maxStability = function (n, edges, k) {

    // Mandatory edges must not form a cycle.
    const base = new DSU(n);

    for (const [u, v, s, must] of edges) {
        if (must === 1) {
            if (!base.union(u, v)) {
                return -1;
            }
        }
    }

    function can(threshold) {

        const dsu = new DSU(n);

        // Include mandatory edges
        for (const [u, v, s, must] of edges) {
            if (must === 1) {
                if (s < threshold) return false;
                dsu.union(u, v);
            }
        }

        const freeEdges = [];
        const upgradeEdges = [];

        for (const [u, v, s, must] of edges) {
            if (must === 1) continue;

            if (s >= threshold) {
                freeEdges.push([u, v]);
            } else if (s * 2 >= threshold) {
                upgradeEdges.push([u, v]);
            }
        }

        let upgradesUsed = 0;

        // Cost 0 edges first
        for (const [u, v] of freeEdges) {
            dsu.union(u, v);
        }

        // Cost 1 edges
        for (const [u, v] of upgradeEdges) {
            if (dsu.union(u, v)) {
                upgradesUsed++;
            }
        }

        return dsu.components === 1 && upgradesUsed <= k;
    }

    let left = 1;
    let right = 200000; // max possible doubled strength
    let ans = -1;

    while (left <= right) {
        const mid = Math.floor((left + right) / 2);

        if (can(mid)) {
            ans = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return ans;
};