function mystrategy(cards, opponentCards, humanCard) {
    var x;
    var myCards = cards.toArray();
    var max = -1;
    for (x in myCards) {
        if (myCards[x] > max){
            max = myCards[x];
        }
    }
    return max;
}
