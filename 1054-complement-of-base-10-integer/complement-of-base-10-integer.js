/**
 * @param {number} n
 * @return {number}
 */
var bitwiseComplement = function(n) {
    if (n === 0) return 1;

    let mask = 0;
    let temp = n;

    while (temp > 0) {
        mask = (mask << 1) | 1;
        temp >>= 1;
    }

    return n ^ mask;
};