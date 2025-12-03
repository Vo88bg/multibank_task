/*
The following program counts the number of times each letter appears in a string.
The program ignores special characters and only counts the letters.
The program is case sensitive.
If the input consists of spaces only, the program will output empty input message.
If the input consists of special characters only, the program will output special characters message.
If the input is null or consists of tabs or new lines, the program will output null message.
The letters are stored in the 'letters' array and repetitions are avoided by checking if the letter is already in the array.
The amount of repetitions is stored in the 'count' array.
The lengths of both arrays are the same, so the program can output the result in a single loop.
After all letters are counted, the program combines the result into a string and outputs it to the console.
*/

let input = "hello, world!";
let stringArr = input.match(/[A-z0-9]/g);
let letters = [];
let count = [];
let letterPosition = 0;


if (input.length == 0) {
    console.log("The input is empty");
} else if (input.length > 0 && stringArr == 0) {
    console.log("The input consists of special characters only.");
} else if (stringArr == null) {
    console.log("The input is null or consists of tabs or new lines.");
} else {
    countCharacters();
}

function countCharacters() {
    for (let i = 0; i < stringArr.length; i++) {
        if (!letters.includes(stringArr[i])) {
            letters[letterPosition] = stringArr[i];
            count.push(1);
            letterPosition++;
        } else {
            let index = letters.indexOf(stringArr[i]);
            letters[index];
            count[index] += 1;
        }

    }
}
    let result = "";
    for (let i = 0; i < letters.length; i++) {
        if (i < letters.length - 1) {
            result += (`${letters[i]}:${count[i]}, `);
        } else {
            result += (`${letters[i]}:${count[i]}`);
        }
    }
    console.log(result);