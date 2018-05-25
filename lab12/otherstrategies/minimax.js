function Node(data, parent) {
    this.data = data;
    this.parent = parent;
    this.children = [];
}

function Tree(data) {
    var node = new Node(data, null);
    this.root = node;
}

function mystrategy(cards, opponentCards, humanCard) {
   /* var whoStarts;
    if (humanCard == -1){
        whoStarts = "me"; //computer
    }
    else {
        whoStarts = "opponent"; //human
    }

    if (cards.length < 5){
        //full tree
    }
    else {
        //3 poziomy
    }*/

    var tree = new Tree(humanCard);
    var i, j;

    //usun karte, o ktorej wiemy, ze ja zagral przeciwnik
    for(var i in opponentCards){
        if(opponentCards[i]==humanCard){
            opponentCards[i] = undefined;
            break;
        }
    }

    for (i = 0; i < cards.length; i++){
        tree.root.children.push(new Node(cards[i], tree.root));
        print("Rodzic:" + tree.root.children[i].data);
        for (j = 0; j < opponentCards.length; j++){
            tree.root.children[i].children.push(new Node(opponentCards[j], tree.root.children[i]));
            print("Dziecko: " + tree.root.children[i].children[j].data);
        }
    }

    return cards[0];
}
