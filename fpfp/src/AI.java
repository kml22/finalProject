
#import "Artificial_Intelligence.h"

@implementation Artificial_Intelligence

@synthesize giruda;
@synthesize goal;
@synthesize knownChingoo;
@synthesize Turn;

@synthesize is_there_Mighty;
@synthesize is_there_Red_Mighty;
@synthesize is_there_Joker;
@synthesize is_there_Jokercall;
@synthesize is_there_Spade_Jokercall;

@synthesize isDeclarer;
@synthesize isFriend;
@synthesize isOtherparty;

-(id) initWithPosition:(NSInteger) pos{
    NSInteger i = 0;
    self = [super init];
    if(self){
        for(i=0;i<53;i++){
            owners_card_map[i] = 0;
            submitted_card_map[i] = -1;
        }
        giruda = -1;
        goal = -1;
        position = pos;
        declarer = -1;
        current_Player = -1;
        chingooCard = -1;
        knownChingoo = -1;
        Turn = 0;
        starter = -1;
        for(i=0;i<4;i++){
            points[i] = 0;
        }
    }
    return self;

}

-(void) setOwnersCard1: (NSInteger) c1 andCard2: (NSInteger) c2 andCard3: (NSInteger) c3 andCard4:(NSInteger) c4 andCard5: (NSInteger) c5 andCard6: (NSInteger) c6 andCard7: (NSInteger) c7 andCard8: (NSInteger) c8 andCard9: (NSInteger) c9 andCard10: (NSInteger) c10{
    owners_card_map[c1] = 1;
    owners_card_map[c2] = 1;
    owners_card_map[c3] = 1;
    owners_card_map[c4] = 1;
    owners_card_map[c5] = 1;
    owners_card_map[c6] = 1;
    owners_card_map[c7] = 1;
    owners_card_map[c8] = 1;
    owners_card_map[c9] = 1;
    owners_card_map[c10] = 1;
    
    //    NSLog(@"%d, %d, %d, %d, %d, %d, %d, %d, %d, %d",c1,c2,c3,c4,c5,c6,c7,c8,c9,c10);
    if(owners_card_map[51] == 1){
        is_there_Mighty = true;
    }
    if(owners_card_map[52] == 1){
        is_there_Joker = true;
    }
    if(owners_card_map[38] == 1){
        is_there_Red_Mighty = true;
    }
    if(owners_card_map[1] == 1){
        is_there_Jokercall = true;
    }
    if(owners_card_map[40]){
        is_there_Spade_Jokercall = true;
    }
}

-(void) setDeclarer:(NSInteger)d{
    self->declarer = d;
    if(self->declarer == self->position){
        self->isDeclarer = YES;
        self->isFriend = NO;
        self->isOtherparty = NO;
    }
    else{
        self->isDeclarer = NO;
        self->isFriend = NO;
        self->isOtherparty = YES;
    }
}

-(void) setMoreThreeCards: (NSInteger) c1 and: (NSInteger) c2 and: (NSInteger) c3{
    owners_card_map[c1] = 1;
    owners_card_map[c2] = 1;
    owners_card_map[c3] = 1;
    
    if(owners_card_map[51] == 1){
        is_there_Mighty = true;
    }
    if(owners_card_map[52] == 1){
        is_there_Joker = true;
    }
    if(owners_card_map[38] == 1){
        is_there_Red_Mighty = true;
    }
    if(owners_card_map[1] == 1){
        is_there_Jokercall = true;
    }
    if(owners_card_map[40]){
        is_there_Spade_Jokercall = true;
    }
}
                     
-(void) chooseAndRemoveThreeCards: (NSInteger*) d1 and: (NSInteger*) d2 and: (NSInteger*) d3{
    NSInteger i=0;
    NSInteger j=0;
    NSInteger high = 0;
    NSInteger low = 0;
    NSInteger priority[13];
    NSMutableString* output = [[NSMutableString alloc] initWithString:@"\nPriority:\n"];
    low = 0;
    high = 13;
    switch(giruda){
        case SPADE:
            //기루다를 제외한 카드의 한 종류가 딱 3개이고 모두 숫자카드라면? 버리고 바로 종료, 그림카드가 있으면 밑의 코드 실행
            //만약 한 종류가 2개 이하이고 모두 숫자카드라면? 우선순위를 제일 낮게 주고 밑의 코드 실행
            
            if(owners_card_map[38] == 1)    priority[low++] = 38;   // Mighty
            if(owners_card_map[52] == 1)    priority[low++] = 52;   // Joker
            for(i=51;i>=39;i--){                                    // Giruda
                if(owners_card_map[i] == 1) priority[low++] = i;
                if(low == high) break;
            }
            
            ///clover///
            for(i=0;i<=7;i++){ //클로버 숫자 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[--high] = i;
                if(low == high) break;
            }
            for(i=12;i>=8;i--){ //클로버 그림 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            
            ///heart///
            for(i=13;i<=20;i++){ //하트 숫자 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[--high] = i;
                if(low == high) break;
            }
            for(i=25;i>=21;i--){ //하트 그림 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            
            ///diamond///
            for(i=26;i<=33;i++){ //다이아몬드 숫자 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[--high] = i;
                if(low == high) break;
            }
            for(i=37;i>=34;i--){ //다이아몬드 그림 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            break;
            //기루다 빼고 모든 카드 모양의 그림카드를 배열 앞에서부터 채워 넣은 후 나머지 숫자 카드를 채워 넣음
            
            ////여기서부터 수정/// 기루다를 제외한 나머지 카드를 앞에서부터 채워넣음 연속x
            
        case DIAMOND:
            if(owners_card_map[51] == 1)    priority[low++] = 51;   // Mighty
            if(owners_card_map[52] == 1)    priority[low++] = 52;   // Joker
            for(i=38;i>=26;i--){                                    // Giruda
                if(owners_card_map[i] == 1) priority[low++] = i;
                if(low == high) break;
            }
            
            ///clover///
            for(i=0;i<=7;i++){ //클로버 숫자 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[--high] = i;
                if(low == high) break;
            }
            for(i=12;i>=8;i--){ //클로버 그림 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            
            ///heart///
            for(i=13;i<=20;i++){ //하트 숫자 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[--high] = i;
                if(low == high) break;
            }
            for(i=25;i>=21;i--){ //하트 그림 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            
            ///spade///
            for(i=39;i<=46;i++){ //스페이드 숫자 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[--high] = i;
                if(low == high) break;
            }
            for(i=50;i>=47;i--){ //스페이드 그림 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            break;
        case HEART:
            if(owners_card_map[51] == 1)    priority[low++] = 51;   // Mighty
            if(owners_card_map[52] == 1)    priority[low++] = 52;   // Joker
            for(i=25;i>=13;i--){                                    // Giruda
                if(owners_card_map[i] == 1) priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            
            ///clover///
            for(i=0;i<=7;i++){ //클로버 숫자 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[--high] = i;
                if(low == high) break;
            }
            for(i=12;i>=8;i--){ //클로버 그림 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            
            ///spade///
            for(i=39;i<=46;i++){ //spade 숫자 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[--high] = i;
                if(low == high) break;
            }
            for(i=50;i>=47;i--){ //spade 그림 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            
            ///diamond///
            for(i=26;i<=33;i++){ //다이아몬드 숫자 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[--high] = i;
                if(low == high) break;
            }
            for(i=38;i>=34;i--){ //다이아몬드 그림 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            break;
        case CLOVER:
            if(owners_card_map[51] == 1)    priority[low++] = 51;   // Mighty
            if(owners_card_map[52] == 1)    priority[low++] = 52;   // Joker
            for(i=12;i>=0;i--){                                    // Giruda
                if(owners_card_map[i] == 1) priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            
            ///spade///
            for(i=39;i<=46;i++){ //spade 숫자 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[--high] = i;
                if(low == high) break;
            }
            for(i=50;i>=47;i--){ //spade 그림 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            
            ///heart///
            for(i=13;i<=20;i++){ //하트 숫자 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[--high] = i;
                if(low == high) break;
            }
            for(i=25;i>=21;i--){ //하트 그림 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            
            ///diamond///
            for(i=26;i<=33;i++){ //다이아몬드 숫자 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[--high] = i;
                if(low == high) break;
            }
            for(i=38;i>=34;i--){ //다이아몬드 그림 카드 몇 개인지 세기
                if(owners_card_map[i] == 1)
                    priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            break;
        default:
            break;
    }
    owners_card_map[*d1=priority[10]] = 0;
    owners_card_map[*d2=priority[11]] = 0;
    owners_card_map[*d3=priority[12]] = 0;
    self->submitted_card_map[priority[10]] = self->position;
    self->submitted_card_map[priority[11]] = self->position;
    self->submitted_card_map[priority[12]] = self->position;
    
    for(i=0;i<13;i++){ //여기서부터는 손 안대도 됨
        switch(priority[i] / 13){
            case 0:
                [output appendFormat:@"C"];
                break;
            case 1:
                [output appendFormat:@"H"];
                break;
            case 2:
                [output appendFormat:@"D"];
                break;
            case 3:
                [output appendFormat:@"S"];
                break;
            case 4:
                [output appendFormat:@"JKR, "];
                break;
        }
        if(priority[i] != 52){
            j=priority[i]%13;
            if(j >= 0 && j <= 7)    [output appendFormat:@" %ld, ",j+2];
            else if(j == 8)         [output appendString:@"10, "];
            else if(j == 9)         [output appendString:@" J, "];
            else if(j == 10)        [output appendString:@" Q, "];
            else if(j == 11)        [output appendString:@" K, "];
            else if(j == 12)        [output appendString:@" A, "];
            
        }
    }
    NSLog(@"%@",output);
}

-(void) setChingooCard:(NSInteger)cc{
    self->chingooCard = cc;
    if(owners_card_map[cc]){
        self->isFriend = YES;
        self->isOtherparty = NO;
    }
    else    self->isFriend = NO;
}

-(NSInteger) chooseFriend{
    if(giruda == SPADE){
        if(!is_there_Red_Mighty)    return chingooCard = 0x100+38;
    }
    else{
        if(!is_there_Mighty)    return chingooCard = 0x100+51;
    }
    if(!is_there_Joker) return chingooCard = 0x100+52;
    switch(giruda){
        case SPADE:
            if(owners_card_map[51] == 0)    return chingooCard = 0x100+51;
            else if(owners_card_map[50] == 0)   return chingooCard = 0x100+50;
            else if((owners_card_map[12] == 1) && (owners_card_map[25] == 1))   return chingooCard = 0x300;
            else{
                if(owners_card_map[25] == 0)    return chingooCard = 0x100+25;
                else if(owners_card_map[12] == 0)   return chingooCard = 0x100+12;
            }
        case DIAMOND:
            if(owners_card_map[38] == 0)    return chingooCard = 0x100+38;
            else if(owners_card_map[37] == 0)   return chingooCard = 0x100+37;
            else if((owners_card_map[12] == 1) && (owners_card_map[25] == 1))   return chingooCard = 0x300;
            else{
                if(owners_card_map[25] == 0)    return chingooCard = 0x100+25;
                else if(owners_card_map[12] == 0)   return chingooCard = 0x100+12;
            }
        case HEART:
            if(owners_card_map[25] == 0)    return chingooCard = 0x100+25;
            else if(owners_card_map[24] == 0)   return chingooCard = 0x100+24;
            else if((owners_card_map[12] == 1) && (owners_card_map[38] == 1))   return chingooCard = 0x300;
            else{
                if(owners_card_map[38] == 0)    return chingooCard = 0x100+38;
                else if(owners_card_map[12] == 0)   return chingooCard = 0x100+12;
            }
        case CLOVER:
            if(owners_card_map[12] == 0)    return chingooCard = 0x100+12;
            else if(owners_card_map[11] == 0)   return chingooCard = 0x100+11;
            else if((owners_card_map[38] == 1) && (owners_card_map[25] == 1))   return chingooCard = 0x300;
            else{
                if(owners_card_map[38] == 0)    return chingooCard = 0x100+38;
                else if(owners_card_map[25] == 0)   return chingooCard = 0x100+25;
            }
        default:
            break;
    }
    return chingooCard = 0x400;
}

-(void) removeOwnersCard: (NSInteger) card{
    owners_card_map[card] = 0;
}

-(NSInteger) submitACard: (NSInteger) firstShape JokerShape:(NSInteger*) jkshape{
    NSInteger i=0;
    NSInteger j=0;
    NSInteger k=0;
    
    NSInteger chosenCard = 0;
    NSInteger jjangCard = 0;
    
    self->numOfPossibleCards = 0;
    self->sizeOfSpadeJjangCard = 0;
    self->sizeOfSpadePointCard = 0;
    self->sizeOfSpadeMoolCard = 0;
    self->sizeOfDiamondJjangCard = 0;
    self->sizeOfDiamondPointCard = 0;
    self->sizeOfDiamondMoolCard = 0;
    self->sizeOfHeartJjangCard = 0;
    self->sizeOfHeartPointCard = 0;
    self->sizeOfHeartMoolCard = 0;
    self->sizeOfCloverJjangCard = 0;
    self->sizeOfCloverPointCard = 0;
    self->sizeOfCloverMoolCard = 0;
    
    for(i=0;i<10;i++){
        self->possibleCardIndex[i] = -1;
        self->spadeJjangCard[i] = -1;
        self->spadePointCard[i] = -1;
        self->spadeMoolCard[i] = -1;
        self->diamondJjangCard[i] = -1;
        self->diamondPointCard[i] = -1;
        self->diamondMoolCard[i] = -1;
        self->heartJjangCard[i] = -1;
        self->heartPointCard[i] = -1;
        self->heartMoolCard[i] = -1;
        self->cloverJjangCard[i] = -1;
        self->cloverPointCard[i] = -1;
        self->cloverMoolCard[i] = -1;
    }
    
    NSMutableString* output = [[NSMutableString alloc] initWithFormat:@"\n== Player %ld ==\nSubmitted Card: ", self->position];
    for(i=starter; i!= position;i=(i+1)%5){
        [output appendFormat:@"%@, ",showSimpleCardName(submittedCardinEveryTurn[i])];
    }
    numOfPossibleCards = 0;
    if(self->starter == self->position){
        if((self->Turn == 1) && (giruda != NOGIRUDA)){
            for(i=0;i<53;i++){
                if(owners_card_map[i]){
                    switch(giruda){//첫 판에 기루다를 못 내니까
                        case SPADE:
                            if(i/13 != 3)  possibleCardIndex[numOfPossibleCards++] = i;
                            break;
                        case DIAMOND:
                            if(i/13 != 2)  possibleCardIndex[numOfPossibleCards++] = i;
                            break;
                        case HEART:
                            if(i/13 != 1)  possibleCardIndex[numOfPossibleCards++] = i;
                            break;
                        case CLOVER:
                            if(i/13 != 0)  possibleCardIndex[numOfPossibleCards++] = i;
                            break;
                    }
                }
            }
            if(numOfPossibleCards == 0){
                for(i=0;i<53;i++){
                    if(owners_card_map[i])  possibleCardIndex[numOfPossibleCards++] = i;
                }
            }
            else if(numOfPossibleCards == 1){
                if(owners_card_map[52] == 1){
                    for(i=0, numOfPossibleCards = 0; i<53;i++){
                        if(owners_card_map[i])  possibleCardIndex[numOfPossibleCards++] = i;
                    }

                }
            }
        }
        else{//선인데 첫 턴이 아닐 경우
            for(i=0;i<53;i++){
                if(owners_card_map[i])  possibleCardIndex[numOfPossibleCards++] = i;
            }
        }
    }
    else{//선이 아닐 경우
        switch(firstShape){
            case SPADE:
                for(i=39;i<52;i++){
                    if(owners_card_map[i]){
                        possibleCardIndex[numOfPossibleCards++] = i;
                    }
                }
                if(numOfPossibleCards == 0){
                    for(i=0;i<53;i++){
                        if(owners_card_map[i])  possibleCardIndex[numOfPossibleCards++] = i;
                    }
                }
                else{
                    if((giruda == SPADE) && (owners_card_map[39]))    possibleCardIndex[numOfPossibleCards++] = 39;
                    if(owners_card_map[52]) possibleCardIndex[numOfPossibleCards++] = 52;
                }
                break;
            case DIAMOND:
                for(i=26;i<39;i++){
                    if(owners_card_map[i]){
                        possibleCardIndex[numOfPossibleCards++] = i;
                    }
                }
                if(numOfPossibleCards == 0){
                    for(i=0;i<53;i++){
                        if(owners_card_map[i])  possibleCardIndex[numOfPossibleCards++] = i;
                    }
                }
                else{
                    if((giruda != SPADE) && (owners_card_map[51]))    possibleCardIndex[numOfPossibleCards++] = 51;
                    if(owners_card_map[52]) possibleCardIndex[numOfPossibleCards++] = 52;
                }
                break;
            case HEART:
                for(i=13;i<26;i++){
                    if(owners_card_map[i]){
                        possibleCardIndex[numOfPossibleCards++] = i;
                    }
                }
                if(numOfPossibleCards == 0){
                    for(i=0;i<53;i++){
                        if(owners_card_map[i])  possibleCardIndex[numOfPossibleCards++] = i;
                    }
                }
                else{
                    if((giruda != SPADE) && (owners_card_map[51]))    possibleCardIndex[numOfPossibleCards++] = 51;
                    else if((giruda == SPADE) && (owners_card_map[38]))    possibleCardIndex[numOfPossibleCards++] = 38;
                    if(owners_card_map[52]) possibleCardIndex[numOfPossibleCards++] = 52;
                }
                break;
            case CLOVER:
                for(i=0;i<13;i++){
                    if(owners_card_map[i]){
                        possibleCardIndex[numOfPossibleCards++] = i;
                    }
                }
                if(numOfPossibleCards == 0){
                    for(i=0;i<53;i++){
                        if(owners_card_map[i])  possibleCardIndex[numOfPossibleCards++] = i;
                    }
                }
                else{
                    if((giruda != SPADE) && (owners_card_map[51]))    possibleCardIndex[numOfPossibleCards++] = 51;
                    else if((giruda == SPADE) && (owners_card_map[38]))    possibleCardIndex[numOfPossibleCards++] = 38;
                    if(owners_card_map[52]) possibleCardIndex[numOfPossibleCards++] = 52;
                }
                break;
            default:
                for(i=0;i<53;i++){
                    if(owners_card_map[i])  possibleCardIndex[numOfPossibleCards++] = i;
                }
                break;
        }
    }
    [output appendString:@"\nPossible Cards: "];
    for(i=0;i<numOfPossibleCards;i++){
        [output appendFormat:@"%@, ",showSimpleCardName(possibleCardIndex[i])];
    }

    sizeOfSpadeJjangCard = 0;
    sizeOfDiamondJjangCard = 0;
    sizeOfHeartJjangCard = 0;
    sizeOfCloverJjangCard = 0;
    sizeOfSpadeMoolCard = 0;
    sizeOfDiamondMoolCard = 0;
    sizeOfHeartMoolCard = 0;
    sizeOfCloverMoolCard = 0;

    ////////////// SPADE ///////////////
    if(self->giruda == SPADE){
        for(jjangCard = 51;jjangCard>=39;jjangCard--){
            if(self->submitted_card_map[jjangCard] == -1)   break;
        }
    }
    else{
        for(jjangCard = 50;jjangCard>=39;jjangCard--){
            if(self->submitted_card_map[jjangCard] == -1)   break;
        }
    }
    if(jjangCard != 38){
        for(i=0; i<numOfPossibleCards;i++){
            if(possibleCardIndex[i] == jjangCard){
                for(j=jjangCard;j>=39;j--){
                    if(owners_card_map[j])  spadeJjangCard[sizeOfSpadeJjangCard++] = j;
                    else    break;
                }
                break;
            }
        }
    }
    if(self->giruda == SPADE){
        for(i=0;i<numOfPossibleCards;i++){
            if((possibleCardIndex[i] >= 39) && (possibleCardIndex[i] <= 51)){
                for(k=39;k<47;k++){
                    if(owners_card_map[k]){
                        if(sizeOfSpadeJjangCard){
                            if(k == spadeJjangCard[sizeOfSpadeJjangCard-1]) break;
                        }
                        spadeMoolCard[sizeOfSpadeMoolCard++] = k;
                    }
                }
                for(k=47;k<=51;k++){
                    if(owners_card_map[k])  spadePointCard[sizeOfSpadePointCard++] = k;
                }
                break;
            }
        }
    }
    else{
        for(i=0;i<numOfPossibleCards;i++){
            if((possibleCardIndex[i] >= 39) && (possibleCardIndex[i] <= 50)){
                for(k=39;k<47;k++){
                    if(owners_card_map[k]){
                        if(sizeOfSpadeJjangCard){
                            if(k == spadeJjangCard[sizeOfSpadeJjangCard-1]) break;
                        }
                        spadeMoolCard[sizeOfSpadeMoolCard++] = k;
                    }
                }
                for(k=47;k<=50;k++){
                    if(owners_card_map[k])  spadePointCard[sizeOfSpadePointCard++] = k;
                }
                break;
            }
        }
    }
    ////////////// DIAMOND ///////////////
    if(self->giruda == SPADE){
        for(jjangCard = 37;jjangCard>=26;jjangCard--){
            if(self->submitted_card_map[jjangCard] == -1)   break;
        }
    }
    else{
        for(jjangCard = 38;jjangCard>=26;jjangCard--){
            if(self->submitted_card_map[jjangCard] == -1)   break;
        }
    }
    if(jjangCard != 25){
        for(i=0; i<numOfPossibleCards;i++){
            if(possibleCardIndex[i] == jjangCard){
                for(j=jjangCard;j>=26;j--){
                    if(owners_card_map[j])  diamondJjangCard[sizeOfDiamondJjangCard++] = j;
                    else    break;
                }
                break;
            }
        }
    }
    if(self->giruda == SPADE){
        for(i=0;i<numOfPossibleCards;i++){
            if((possibleCardIndex[i] >= 26) && (possibleCardIndex[i] <= 37)){
                for(k=26;k<34;k++){
                    if(owners_card_map[k]){
                        if(sizeOfDiamondJjangCard){
                            if(k == diamondJjangCard[sizeOfDiamondJjangCard-1]) break;
                        }
                        diamondMoolCard[sizeOfDiamondMoolCard++] = k;
                    }
                }
                for(k=34;k<=37;k++){
                    if(owners_card_map[k])  diamondPointCard[sizeOfDiamondPointCard++] = k;
                }
                break;
            }
        }
    }
    else{
        for(i=0;i<numOfPossibleCards;i++){
            if((possibleCardIndex[i] >= 26) && (possibleCardIndex[i] <= 38)){
                for(k=26;k<34;k++){
                    if(owners_card_map[k]){
                        if(sizeOfDiamondJjangCard){
                            if(k == diamondJjangCard[sizeOfDiamondJjangCard-1]) break;
                        }
                        diamondMoolCard[sizeOfDiamondMoolCard++] = k;
                    }
                }
                for(k=34;k<=38;k++){
                    if(owners_card_map[k])  diamondPointCard[sizeOfDiamondPointCard++] = k;
                }
                break;
            }
        }
    }
    ////////////// HEART ///////////////
    for(jjangCard = 25;jjangCard>=13;jjangCard--){
        if(self->submitted_card_map[jjangCard] == -1)   break;
    }
    if(jjangCard != 12){
        for(i=0; i<numOfPossibleCards;i++){
            if(possibleCardIndex[i] == jjangCard){
                for(j=jjangCard;j>=13;j--){
                    if(owners_card_map[j])  heartJjangCard[sizeOfHeartJjangCard++] = j;
                    else    break;
                }
                break;
            }
        }
    }
    for(i=0;i<numOfPossibleCards;i++){
        if((possibleCardIndex[i] >= 13) && (possibleCardIndex[i] <= 25)){
            for(k=13;k<21;k++){
                if(owners_card_map[k]){
                    if(sizeOfHeartJjangCard){
                        if(k == heartJjangCard[sizeOfHeartJjangCard-1]) break;
                    }
                    heartMoolCard[sizeOfHeartMoolCard++] = k;
                }
            }
            for(k=21;k<=25;k++){
                if(owners_card_map[k])  heartPointCard[sizeOfHeartPointCard++] = k;
            }
            break;
        }
    }
    ////////////// CLOVER ///////////////
    for(jjangCard = 12;jjangCard>=0;jjangCard--){
        if(self->submitted_card_map[jjangCard] == -1)   break;
    }
    if(jjangCard != -1){
        for(i=0; i<numOfPossibleCards;i++){
            if(possibleCardIndex[i] == jjangCard){
                for(j=jjangCard;j>=0;j--){
                    if(owners_card_map[j])  cloverJjangCard[sizeOfCloverJjangCard++] = j;
                    else    break;
                }
                break;
            }
        }
    }
    for(i=0;i<numOfPossibleCards;i++){
        if((possibleCardIndex[i] >= 0) && (possibleCardIndex[i] <= 12)){
            for(k=0;k<8;k++){
                if(owners_card_map[k]){
                    if(sizeOfCloverJjangCard){
                        if(k == cloverJjangCard[sizeOfCloverJjangCard-1]) break;
                    }
                    cloverMoolCard[sizeOfCloverMoolCard++] = k;
                }
            }
            for(k=8;k<=12;k++){
                if(owners_card_map[k])  cloverPointCard[sizeOfCloverPointCard++] = k;
            }
            break;
        }
    }
    
    [output appendString:@"\nSpade Jjang: "];
    for(i=0;i<sizeOfSpadeJjangCard;i++) [output appendFormat:@"%@, ",showSimpleCardName(spadeJjangCard[i])];
    [output appendString:@"\nSpade Mool : "];
    for(i=0;i<sizeOfSpadeMoolCard;i++) [output appendFormat:@"%@, ",showSimpleCardName(spadeMoolCard[i])];
    [output appendString:@"\nSpade Point: "];
    for(i=0;i<sizeOfSpadePointCard;i++) [output appendFormat:@"%@, ",showSimpleCardName(spadePointCard[i])];
    [output appendString:@"\nDiamond Jjang: "];
    for(i=0;i<sizeOfDiamondJjangCard;i++) [output appendFormat:@"%@, ",showSimpleCardName(diamondJjangCard[i])];
    [output appendString:@"\nDiamond Mool : "];
    for(i=0;i<sizeOfDiamondMoolCard;i++) [output appendFormat:@"%@, ",showSimpleCardName(diamondMoolCard[i])];
    [output appendString:@"\nDiamond Point: "];
    for(i=0;i<sizeOfDiamondPointCard;i++) [output appendFormat:@"%@, ",showSimpleCardName(diamondPointCard[i])];
    [output appendString:@"\nHeart Jjang: "];
    for(i=0;i<sizeOfHeartJjangCard;i++) [output appendFormat:@"%@, ",showSimpleCardName(heartJjangCard[i])];
    [output appendString:@"\nHeart Mool : "];
    for(i=0;i<sizeOfHeartMoolCard;i++) [output appendFormat:@"%@, ",showSimpleCardName(heartMoolCard[i])];
    [output appendString:@"\nHeart Point: "];
    for(i=0;i<sizeOfHeartPointCard;i++) [output appendFormat:@"%@, ",showSimpleCardName(heartPointCard[i])];
    [output appendString:@"\nClover Jjang: "];
    for(i=0;i<sizeOfCloverJjangCard;i++) [output appendFormat:@"%@, ",showSimpleCardName(cloverJjangCard[i])];
    [output appendString:@"\nClover Mool : "];
    for(i=0;i<sizeOfCloverMoolCard;i++) [output appendFormat:@"%@, ",showSimpleCardName(cloverMoolCard[i])];
    [output appendString:@"\nClover Point: "];
    for(i=0;i<sizeOfCloverPointCard;i++) [output appendFormat:@"%@, ",showSimpleCardName(cloverPointCard[i])];
    
    NSLog(@"%@",output);
    
    switch(self->Turn){
        case 1:
            chosenCard = [self choosingCardForTurn1:firstShape JokerShape: jkshape];
            break;
        default:
            if(isDeclarer){
                chosenCard = possibleCardIndex[i = arc4random() % numOfPossibleCards];
                if(chosenCard == 52){
                    switch(arc4random() % 4){
                        case 0:
                            *jkshape = CLOVER;
                            break;
                        case 1:
                            *jkshape = HEART;
                            break;
                        case 2:
                            *jkshape = DIAMOND;
                            break;
                        case 3:
                            *jkshape = SPADE;
                            break;
                    }
                }            }
            else if(isFriend){
                chosenCard = possibleCardIndex[arc4random() % numOfPossibleCards];
                if(chosenCard == 52){
                    switch(arc4random() % 4){
                        case 0:
                            *jkshape = CLOVER;
                            break;
                        case 1:
                            *jkshape = HEART;
                            break;
                        case 2:
                            *jkshape = DIAMOND;
                            break;
                        case 3:
                            *jkshape = SPADE;
                            break;
                    }
                }
            }
            else{
                chosenCard = possibleCardIndex[arc4random() % numOfPossibleCards];
                if(chosenCard == 52){
                    switch(arc4random() % 4){
                        case 0:
                            *jkshape = CLOVER;
                            break;
                        case 1:
                            *jkshape = HEART;
                            break;
                        case 2:
                            *jkshape = DIAMOND;
                            break;
                        case 3:
                            *jkshape = SPADE;
                            break;
                    }
                }
            }
            break;
    }
    self->owners_card_map[chosenCard] = 0;
    self->submittedCardinEveryTurn[self->position] = chosenCard;
    return chosenCard;
}

-(NSInteger) choosingCardForTurn1: (NSInteger) firstShape JokerShape:(NSInteger*) jkshape{
    NSInteger chosenCard = 0;
    NSInteger starterCard = 0;
    if(self->isDeclarer){
        if(sizeOfCloverJjangCard){
            chosenCard = cloverJjangCard[0];
        }
        else if(sizeOfHeartJjangCard){
            chosenCard = heartJjangCard[0];
        }
        else if(sizeOfDiamondJjangCard){
            chosenCard = diamondJjangCard[0];
        }
        else if(sizeOfSpadeJjangCard){
            chosenCard = spadeJjangCard[0];
        }
        else if(sizeOfHeartMoolCard){
            chosenCard = heartMoolCard[0];
        }
        else if(sizeOfCloverMoolCard) {
            chosenCard = cloverMoolCard[0];
        }
        else if(sizeOfDiamondMoolCard){
            chosenCard = diamondMoolCard[0];
        }
        else if(sizeOfSpadeMoolCard){
            chosenCard = spadeMoolCard[0];
        }
        else if((self->giruda == SPADE) && is_there_Red_Mighty){
            chosenCard = 38;
        }
        else if((self->giruda != SPADE) && is_there_Mighty){
            chosenCard = 51;
        }
        else if(owners_card_map[52]){
            chosenCard = 52;
            *jkshape = self->giruda;
        }
        else{
            if((self->giruda == SPADE) && (self->chingooCard == (0x100+38))){
                if(sizeOfDiamondPointCard){
                    chosenCard = diamondPointCard[0];
                }
            }
            else if((self->giruda != SPADE) && (self->chingooCard == (0x100+51))){
                if(sizeOfSpadePointCard){
                    chosenCard = spadePointCard[0];
                }
            }
            else{
                if(sizeOfSpadePointCard){
                    chosenCard = spadePointCard[0];
                }
                else if(sizeOfDiamondPointCard){
                    chosenCard = diamondPointCard[0];
                }
                else if(sizeOfHeartPointCard){
                    chosenCard = heartPointCard[0];
                }
                else if(sizeOfCloverPointCard){
                    chosenCard = cloverPointCard[0];
                }
                else{
                    chosenCard = possibleCardIndex[arc4random() % numOfPossibleCards];
                }
            }
        }
    }
    else if(isFriend){
        starterCard = self->submittedCardinEveryTurn[self->declarer];
        if((starterCard == 51) || (starterCard == 50)){
            if(sizeOfSpadePointCard){
                chosenCard = spadePointCard[0];
            }
            else if(sizeOfSpadeMoolCard){
                chosenCard = spadeMoolCard[0];
            }
            else if(is_there_Mighty){
                chosenCard = 51;
            }
            else if((self->giruda != DIAMOND) && (sizeOfDiamondPointCard != 0)){
                chosenCard = diamondPointCard[0];
            }
            else if((self->giruda != HEART) && (sizeOfHeartPointCard != 0)){
                chosenCard = heartPointCard[0];
            }
            else if((self->giruda != CLOVER) && (sizeOfCloverPointCard != 0)){
                chosenCard = cloverPointCard[0];
            }
            else if((self->giruda != DIAMOND) && (sizeOfDiamondMoolCard != 0)){
                chosenCard = diamondMoolCard[0];
            }
            else if((self->giruda != HEART) && (sizeOfHeartMoolCard != 0)){
                chosenCard = heartMoolCard[0];
            }
            else if((self->giruda != CLOVER) && (sizeOfCloverMoolCard != 0)){
                chosenCard = cloverMoolCard[0];
            }
            else{
                if(possibleCardIndex[0] == 52){
                    chosenCard = possibleCardIndex[1];
                }
                else{
                    chosenCard = possibleCardIndex[0];
                }
            }
        }
        else if((starterCard == 38) || ((self->giruda == SPADE) && (starterCard == 37))){
            if(sizeOfDiamondPointCard){
                chosenCard = diamondPointCard[0];
            }
            else if(sizeOfDiamondMoolCard){
                chosenCard = diamondMoolCard[0];
            }
            else if(is_there_Red_Mighty){
                chosenCard = 38;
            }
            else if((self->giruda != SPADE) && (sizeOfSpadePointCard != 0)){
                chosenCard = spadePointCard[0];
            }
            else if((self->giruda != HEART) && (sizeOfHeartPointCard != 0)){
                chosenCard = heartPointCard[0];
            }
            else if((self->giruda != CLOVER) && (sizeOfCloverPointCard != 0)){
                chosenCard = cloverPointCard[0];
            }
            else if((self->giruda != SPADE) && (sizeOfSpadeMoolCard != 0)){
                chosenCard = spadeMoolCard[0];
            }
            else if((self->giruda != HEART) && (sizeOfHeartMoolCard != 0)){
                chosenCard = heartMoolCard[0];
            }
            else if((self->giruda != CLOVER) && (sizeOfCloverMoolCard != 0)){
                chosenCard = cloverMoolCard[0];
            }
            else{
                if(possibleCardIndex[0] == 52){
                    chosenCard = possibleCardIndex[1];
                }
                else{
                    chosenCard = possibleCardIndex[0];
                }
            }
        }
        else{
            chosenCard = possibleCardIndex[arc4random() % numOfPossibleCards];
            if(chosenCard == 52){
                switch(arc4random() % 4){
                    case 0:
                        *jkshape = CLOVER;
                        break;
                    case 1:
                        *jkshape = HEART;
                        break;
                    case 2:
                        *jkshape = DIAMOND;
                        break;
                    case 3:
                        *jkshape = SPADE;
                        break;
                }
            }
        }
    }
    else{
        chosenCard = possibleCardIndex[arc4random() % numOfPossibleCards];
        if(chosenCard == 52){
            switch(arc4random() % 4){
                case 0:
                    *jkshape = CLOVER;
                    break;
                case 1:
                    *jkshape = HEART;
                    break;
                case 2:
                    *jkshape = DIAMOND;
                    break;
                case 3:
                    *jkshape = SPADE;
                    break;
            }
        }
    }
    return chosenCard;
}

-(void) startFirstTurn:(NSInteger) ST{
    NSInteger i=0;
    for(i=0;i<5;i++)    submittedCardinEveryTurn[i] = -1;
    self->current_Player = self->starter = ST;
    self->Turn = 1;
}

-(void) nextTurn:(NSInteger) ST{
    NSInteger i=0;
    for(i=0;i<5;i++){
        self->submitted_card_map[self->submittedCardinEveryTurn[i]] = i;
        submittedCardinEveryTurn[i] = -1;
    }
    self->current_Player = self->starter = ST;
    self->Turn++;
}

-(void) submittedCard:(NSInteger) cardNumber{
    self->submittedCardinEveryTurn[current_Player] = cardNumber;
    current_Player = (current_Player+1)%5;
}

-(bool) jokercall{
    if(owners_card_map[52]){
        owners_card_map[52] = 0;
        return YES;
    }
    else{
        return NO;
    }
}

-(void) addPoint: (NSInteger) index and:(NSInteger) p{
    points[index] += p;
}

-(NSInteger) getOwnersCardMap: (NSInteger) card{
    return owners_card_map[card];
}

-(NSInteger) getPoint:(NSInteger)index{
    return points[index];
}

-(declaration_t) declaring_AI:(NSInteger) pastGoal{
    NSInteger i=0;
    NSInteger tmpGoal = 0;
    NSInteger gapGoal = 0;
    NSInteger num_of_spade = 0;
    NSInteger num_of_diamond = 0;
    NSInteger num_of_heart = 0;
    NSInteger num_of_clover = 0;
    declaration_t decision;
    
    for(num_of_clover=0,i=0;i<13;i++){
        num_of_clover += owners_card_map[i];
    }
    for(num_of_heart=0,i=13;i<26;i++){
        num_of_heart += owners_card_map[i];
    }
    for(num_of_diamond=0,i=26;i<39;i++){
        num_of_diamond += owners_card_map[i];
    }
    for(num_of_spade=0,i=39;i<52;i++){
        num_of_spade += owners_card_map[i];
    }
    if(num_of_spade >= 4){
        tmpGoal = num_of_spade + 9;
        if(is_there_Red_Mighty) tmpGoal++;
        if(is_there_Joker) tmpGoal++;
        if(owners_card_map[50] == 1) tmpGoal++;//추가한 코드
        if(tmpGoal > pastGoal){
            //gapGoal = tmpGoal - pastGoal;
            decision.shp = SPADE;
            //decision.goal = pastGoal + arc4random()%gapGoal + 1;
            decision.goal = pastGoal + 1;//추가한 코드
            return decision;
        }
    }
    if(num_of_diamond >= 4){
        tmpGoal = num_of_diamond+ 9;
        if(is_there_Mighty) tmpGoal++;
        if(is_there_Joker) tmpGoal++;
        if(owners_card_map[37] == 1) tmpGoal++;//추가한 코드
        if(tmpGoal > pastGoal){
            //gapGoal = tmpGoal - pastGoal;
            decision.shp = DIAMOND;
            //decision.goal = pastGoal + arc4random()%gapGoal + 1;
            decision.goal = pastGoal + 1;//추가한 코드
            return decision;
        }
    }
    if(num_of_heart >= 4){
        tmpGoal = num_of_heart + 9;
        if(is_there_Mighty) tmpGoal++;
        if(is_there_Joker) tmpGoal++;
        if(owners_card_map[24] == 1) tmpGoal++;//추가한 코드
        if(tmpGoal > pastGoal){
            //gapGoal = tmpGoal - pastGoal;
            decision.shp = HEART;
            //decision.goal = pastGoal + arc4random()%gapGoal + 1;
            decision.goal = pastGoal + 1;//추가한 코드
            return decision;
        }
    }
    if(num_of_clover >= 4){
        tmpGoal = num_of_clover + 9;
        if(is_there_Mighty) tmpGoal++;
        if(is_there_Joker) tmpGoal++;
        if(owners_card_map[11] == 1) tmpGoal++;//추가한 코드
        if(tmpGoal > pastGoal){
            //gapGoal = tmpGoal - pastGoal;
            decision.shp = CLOVER;
            //decision.goal = pastGoal + arc4random()%gapGoal + 1;
            decision.goal = pastGoal + 1;//추가한 코드
            return decision;
        }
    }
    decision.shp = -1;
    decision.goal = -1;
    return decision;
}

@end
