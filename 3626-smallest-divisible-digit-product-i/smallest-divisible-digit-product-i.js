/**
 * @param {number} n
 * @param {number} t
 * @return {number}
 */
var smallestNumber = function(n, t) {
    
    const digitProduct = (num) => {
        let product = 1;

        while (num > 0) {
            product *= num % 10;
            num = Math.floor(num / 10);
        }

        return product;
    };

    let current = n;

    while (true) {
        if (digitProduct(current) % t === 0) {
            return current;
        }
        current++;
    }
};