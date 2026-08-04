const MOD: i64 = 1_000_000_007;

struct Fancy {
    seq: Vec<i64>,
    mul: i64,
    add: i64,
}

impl Fancy {
    fn new() -> Self {
        Self {
            seq: Vec::new(),
            mul: 1,
            add: 0,
        }
    }

    fn mod_pow(mut base: i64, mut exp: i64) -> i64 {
        let mut res = 1i64;

        while exp > 0 {
            if exp & 1 == 1 {
                res = res * base % MOD;
            }
            base = base * base % MOD;
            exp >>= 1;
        }

        res
    }

    fn append(&mut self, val: i32) {
        let inv_mul = Self::mod_pow(self.mul, MOD - 2);

        let mut stored = (val as i64 - self.add) % MOD;
        if stored < 0 {
            stored += MOD;
        }

        stored = stored * inv_mul % MOD;
        self.seq.push(stored);
    }

    fn add_all(&mut self, inc: i32) {
        self.add = (self.add + inc as i64) % MOD;
    }

    fn mult_all(&mut self, m: i32) {
        self.mul = self.mul * m as i64 % MOD;
        self.add = self.add * m as i64 % MOD;
    }

    fn get_index(&self, idx: i32) -> i32 {
        let idx = idx as usize;

        if idx >= self.seq.len() {
            return -1;
        }

        ((self.seq[idx] * self.mul + self.add) % MOD) as i32
    }
}