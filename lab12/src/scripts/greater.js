function mystrategy(cards, opponentCards, humanCard) {
    var x;
    var myCards = cards.toArray();
    var maxCard;
    if (humanCard != -1){
        maxCard = humanCard;
    }
    else {
        var max = -1;
        for (x in opponentCards) {
            if (opponentCards[x] > max){
                max = opponentCards[x];
            }
        }
        maxCard = max;
    }

    var greaterThanHumanCard = [];
    for (x in myCards) {
        if (myCards[x] > maxCard){
            greaterThanHumanCard.push(myCards[x]);
        }
    }

    var min = Math.pow(2,32) - 1;
    if (greaterThanHumanCard.length == 0){
        for (x in myCards) {
            if (myCards[x] < min){
                min = myCards[x];
            }
        }
    }
    else {
        for (x in greaterThanHumanCard) {
            if (greaterThanHumanCard[x] < min){
                min = greaterThanHumanCard[x];
            }
        }
    }

    return min;
}
