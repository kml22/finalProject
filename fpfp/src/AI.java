//
//  
//  
//
//  
//  
//

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
            if(owners_card_map[38] == 1)    priority[low++] = 38;   // Mighty
            if(owners_card_map[52] == 1)    priority[low++] = 52;   // Joker
            for(i=51;i>=39;i--){                                    // Giruda
                if(owners_card_map[i] == 1) priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            for(i=37;i>=26;i--){                                    //Diamond
                if(owners_card_map[i] == 1) priority[low++] = i;
                else                        break;
                if(low == high) break;
            }
            if(low == high) break;
            for(j=26;j<=i;j++){
                if(owners_card_map[j] == 1) priority[--high] = j;
                if(low == high)   break;
            }
            if(low == high)   break;
            for(i=25;i>=13;i--){                                    //Heart
                if(owners_card_map[i] == 1) priority[low++] = i;
                else                        break;
                if(low == high)   break;
            }
            if(low == high) break;
            for(j=13;j<=i;j++){
                if(owners_card_map[j] == 1) priority[--high] = j;
                if(low == high)   break;
            }
            if(low == high)   break;
            for(i=12;i>=0;i--){                                     //Clover
                if(owners_card_map[i] == 1) priority[--high] = i;
                else                        break;
                if(low == high)   break;
            }
            if(low == high) break;
            for(j=0;j<=i;j++){
                if(owners_card_map[j] == 1) priority[--high] = j;
                if(low == high)   break;
            }
            if(low == high)   break;
            break;
        case DIAMOND:
            if(owners_card_map[51] == 1)    priority[low++] = 51;   // Mighty
            if(owners_card_map[52] == 1)    priority[low++] = 52;   // Joker
            for(i=38;i>=26;i--){                                    // Giruda
                if(owners_card_map[i] == 1) priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            for(i=50;i>=39;i--){                                    //Spade
                if(owners_card_map[i] == 1) priority[low++] = i;
                else                        break;
                if(low == high) break;
            }
            if(low == high) break;
            for(j=39;j<=i;j++){
                if(owners_card_map[j] == 1) priority[--high] = j;
                if(low == high)   break;
            }
            if(low == high)   break;
            for(i=25;i>=13;i--){                                    //Heart
                if(owners_card_map[i] == 1) priority[low++] = i;
                else                        break;
                if(low == high)   break;
            }
            if(low == high) break;
            for(j=13;j<=i;j++){
                if(owners_card_map[j] == 1) priority[--high] = j;
                if(low == high)   break;
            }
            if(low == high)   break;
            for(i=12;i>=0;i--){                                     //Clover
                if(owners_card_map[i] == 1) priority[low++] = i;
                else                        break;
                if(low == high)   break;
            }
            if(low == high) break;
            for(j=0;j<=i;j++){
                if(owners_card_map[j] == 1) priority[--high] = j;
                if(low == high)   break;
            }
            if(low == high)   break;
            break;
        case HEART:
            if(owners_card_map[51] == 1)    priority[low++] = 51;   // Mighty
            if(owners_card_map[52] == 1)    priority[low++] = 52;   // Joker
            for(i=25;i>=13;i--){                                    // Giruda
                if(owners_card_map[i] == 1) priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            for(i=50;i>=39;i--){                                    //Spade
                if(owners_card_map[i] == 1) priority[low++] = i;
                else                        break;
                if(low == high) break;
            }
            if(low == high) break;
            for(j=39;j<=i;j++){
                if(owners_card_map[j] == 1) priority[--high] = j;
                if(low == high)   break;
            }
            if(low == high)   break;
            for(i=38;i>=26;i--){                                    //Diamond
                if(owners_card_map[i] == 1) priority[low++] = i;
                else                        break;
                if(low == high)   break;
            }
            if(low == high) break;
            for(j=26;j<=i;j++){
                if(owners_card_map[j] == 1) priority[--high] = j;
                if(low == high)   break;
            }
            if(low == high)   break;
            for(i=12;i>=0;i--){                                     //Clover
                if(owners_card_map[i] == 1) priority[low++] = i;
                else                        break;
                if(low == high)   break;
            }
            if(low == high) break;
            for(j=0;j<=i;j++){
                if(owners_card_map[j] == 1) priority[--high] = j;
                if(low == high)   break;
            }
            if(low == high)   break;
            break;
        case CLOVER:
            if(owners_card_map[51] == 1)    priority[low++] = 51;   // Mighty
            if(owners_card_map[52] == 1)    priority[low++] = 52;   // Joker
            for(i=12;i>=0;i--){                                    // Giruda
                if(owners_card_map[i] == 1) priority[low++] = i;
                if(low == high) break;
            }
            if(low == high) break;
            for(i=50;i>=39;i--){                                    //Spade
                if(owners_card_map[i] == 1) priority[low++] = i;
                else                        break;
                if(low == high) break;
            }
            if(low == high) break;
            for(j=39;j<=i;j++){
                if(owners_card_map[j] == 1) priority[--high] = j;
                if(low == high)   break;
            }
            if(low == high)   break;
            for(i=38;i>=26;i--){                                    //Diamond
                if(owners_card_map[i] == 1) priority[low++] = i;
                else                        break;
                if(low == high)   break;
            }
            if(low == high) break;
            for(j=26;j<=i;j++){
                if(owners_card_map[j] == 1) priority[--high] = j;
                if(low == high)   break;
            }
            if(low == high)   break;
            for(i=25;i>=13;i--){                                    //Heart
                if(owners_card_map[i] == 1) priority[low++] = i;
                else                        break;
                if(low == high)   break;
            }
            if(low == high) break;
            for(j=13;j<=i;j++){
                if(owners_card_map[j] == 1) priority[--high] = j;
                if(low == high)   break;
            }
            if(low == high)   break;
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
    
    for(i=0;i<13;i++){
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
    NSInteger sizeOfAceCard = 0;
    NSInteger sizeOfKingCard = 0;
    NSInteger i=0;
    
    if(giruda == SPADE){                                // if(self->giruda == SPADE) 와 같음
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
            else if((owners_card_map[12] == 0) && (owners_card_map[25] == 0)){                                     // ** A 대신 기루다 프렌드 선언이 더 나을 때는??
                if(owners_card_map[11] == 1) return chingooCard = 0x100+12;
                else if(owners_card_map[24] == 1) return chingooCard = 0x100+25;
                else if(owners_card_map[10] == 1) return chingooCard = 0x100+12;
                else if(owners_card_map[23] == 1) return chingooCard = 0x100+25;
                else{
                    if(sizeOfCloverPointCard >= sizeOfHeartPointCard) return chingooCard = 0x100+12; // @@ 프렌드 정하기 전에 카드 size 구하는지 확인! 갯수 안 세면 코드 바꿔야 함
                    else return chingooCard = 0x100+25;
                }
            }
            else if((owners_card_map[12] == 0) || (owners_card_map[25] == 0)){                                     // 클로버A,하트A 둘 다 없는 게 아니라 (한쪽만 없는 거라면)
                if(owners_card_map[25] == 0)    return chingooCard = 0x100+25;                                     // 하트A만 없는 거라면 -> 하트A 프렌드
                else if(owners_card_map[12] == 0)   return chingooCard = 0x100+12;                                 // 클로버A만 없는 거라면 -> 클로버A 프렌드
            }
            else if((owners_card_map[12] == 1) && (owners_card_map[25] == 1)){                                     // 클로버A,하트A 둘 다 있다면 -> 기루다 카드 프렌드
                for(i=51;i>=39;i--){                      // 마이티와 조커를 둘 다 갖고 있는 상태이므로 기루다 13장 모두 갖고 있을 일은 없음
                    if(owners_card_map[i] == 0){
                        return chingooCard = 0x100+i;
                    }
                }
            }
        case DIAMOND:
            if(owners_card_map[38] == 0)    return chingooCard = 0x100+38;
            else if(owners_card_map[37] == 0)   return chingooCard = 0x100+37;
            else if((owners_card_map[12] == 0) && (owners_card_map[25] == 0)){
                if(owners_card_map[11] == 1) return chingooCard = 0x100+12;
                else if(owners_card_map[24] == 1) return chingooCard = 0x100+25;
                else if(owners_card_map[10] == 1) return chingooCard = 0x100+12;
                else if(owners_card_map[23] == 1) return chingooCard = 0x100+25;
                else{
                    if(sizeOfCloverPointCard >= sizeOfHeartPointCard) return chingooCard = 0x100+12;
                    else return chingooCard = 0x100+25;
                }
            }
            else if((owners_card_map[12] == 0) || (owners_card_map[25] == 0)){                                     // 클로버A,하트A 둘 다 없는 게 아니라 (한쪽만 없는 거라면)
                if(owners_card_map[25] == 0)    return chingooCard = 0x100+25;                                     // 하트A만 없는 거라면 -> 하트A 프렌드
                else if(owners_card_map[12] == 0)   return chingooCard = 0x100+12;                                 // 클로버A만 없는 거라면 -> 클로버A 프렌드
            }
            else if((owners_card_map[12] == 1) && (owners_card_map[25] == 1)){                                     // 클로버A,하트A 둘 다 있다면 -> 기루다 카드 프렌드
                for(i=38;i>=26;i--){                      // 마이티와 조커를 둘 다 갖고 있는 상태이므로 기루다 13장 모두 갖고 있을 일은 없음
                    if(owners_card_map[i] == 0){
                        return chingooCard = 0x100+i;
                    }
                }
            }
        case HEART:
            if(owners_card_map[25] == 0)    return chingooCard = 0x100+25;
            else if(owners_card_map[24] == 0)   return chingooCard = 0x100+24;
            else if((owners_card_map[12] == 0) && (owners_card_map[38] == 0)){
                if(owners_card_map[11] == 1) return chingooCard = 0x100+12;
                else if(owners_card_map[37] == 1) return chingooCard = 0x100+38;
                else if(owners_card_map[10] == 1) return chingooCard = 0x100+12;
                else if(owners_card_map[36] == 1) return chingooCard = 0x100+38;
                else{
                    if(sizeOfCloverPointCard >= sizeOfDiamondPointCard) return chingooCard = 0x100+12;
                    else return chingooCard = 0x100+38;
                }
            }
            else if((owners_card_map[12] == 0) || (owners_card_map[38] == 0)){                                     // 클로버A,다이아A 둘 다 없는 게 아니라 (한쪽만 없는 거라면)
                if(owners_card_map[38] == 0)    return chingooCard = 0x100+38;                                     // 다이아A만 없는 거라면 -> 다이아A 프렌드
                else if(owners_card_map[12] == 0)   return chingooCard = 0x100+12;                                 // 클로버A만 없는 거라면 -> 클로버A 프렌드
            }
            else if((owners_card_map[12] == 1) && (owners_card_map[38] == 1)){                                     // 클로버A,다이아A 둘 다 있다면 -> 기루다 카드 프렌드
                for(i=25;i>=13;i--){                      // 마이티와 조커를 둘 다 갖고 있는 상태이므로 기루다 13장 모두 갖고 있을 일은 없음
                    if(owners_card_map[i] == 0){
                        return chingooCard = 0x100+i;
                    }
                }
            }
        case CLOVER:
            if(owners_card_map[12] == 0)    return chingooCard = 0x100+12;
            else if(owners_card_map[11] == 0)   return chingooCard = 0x100+11;
            else if((owners_card_map[38] == 0) && (owners_card_map[25] == 0)){
                if(owners_card_map[37] == 1) return chingooCard = 0x100+38;
                else if(owners_card_map[24] == 1) return chingooCard = 0x100+25;
                else if(owners_card_map[36] == 1) return chingooCard = 0x100+38;
                else if(owners_card_map[23] == 1) return chingooCard = 0x100+25;
                else{
                    if(sizeOfDiamondPointCard >= sizeOfHeartPointCard) return chingooCard = 0x100+38;
                    else return chingooCard = 0x100+25;
                }
            }
            else if((owners_card_map[38] == 0) || (owners_card_map[25] == 0)){                                     // 다이아A,하트A 둘 다 없는 게 아니라 (한쪽만 없는 거라면)
                if(owners_card_map[25] == 0)    return chingooCard = 0x100+25;                                     // 하트A만 없는 거라면 -> 하트A 프렌드
                else if(owners_card_map[38] == 0)   return chingooCard = 0x100+38;                                 // 다이아A만 없는 거라면 -> 다이아A 프렌드
            }
            else if((owners_card_map[38] == 1) && (owners_card_map[25] == 1)){                                     // 다이아A,하트A 둘 다 있다면 -> 기루다 카드 프렌드
                for(i=12;i>=0;i--){                      // 마이티와 조커를 둘 다 갖고 있는 상태이므로 기루다 13장 모두 갖고 있을 일은 없음
                    if(owners_card_map[i] == 0){
                        return chingooCard = 0x100+i;
                    }
                }
            }
        default:    // "노기루다 & 마이티 있음 & 조커 있음"의 상황
            break;
    }
    if(owners_card_map[51] == 1) sizeOfAceCard++;
    if(owners_card_map[38] == 1) sizeOfAceCard++;
    if(owners_card_map[25] == 1) sizeOfAceCard++;
    if(owners_card_map[12] == 1) sizeOfAceCard++;
    if(owners_card_map[50] == 1) sizeOfKingCard++;
    if(owners_card_map[37] == 1) sizeOfKingCard++;
    if(owners_card_map[24] == 1) sizeOfKingCard++;
    if(owners_card_map[11] == 1) sizeOfKingCard++;
    if(((sizeOfAceCard >= 3) && (sizeOfKingCard >= 3)) || ((sizeOfAceCard >= 4) && (sizeOfKingCard >= 2)))  return chingooCard = 0x400; // 여기에 프렌드까지 지정하면 야당은 거의 진다고 보면 되므로, 난이도 조정으로 노프렌드 설정함
    else{
        if(owners_card_map[51] == 0) return chingooCard = 0x100+51;
        if(owners_card_map[38] == 0) return chingooCard = 0x100+38;
        if(owners_card_map[25] == 0) return chingooCard = 0x100+25;
        if(owners_card_map[12] == 0) return chingooCard = 0x100+12;
        if(owners_card_map[50] == 0) return chingooCard = 0x100+50;
        if(owners_card_map[37] == 0) return chingooCard = 0x100+37;
        if(owners_card_map[24] == 0) return chingooCard = 0x100+24;
        if(owners_card_map[11] == 0) return chingooCard = 0x100+11;
    }
    return chingooCard = 0x400; // 여기에 리턴 꼭 써줘야 오류 안 나서 씀.
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
                    switch(giruda){
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
        else{
            for(i=0;i<53;i++){
                if(owners_card_map[i])  possibleCardIndex[numOfPossibleCards++] = i;
            }
        }
    }
    else{
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
                else{       // 낼 수 있는 스페이드 카드가 있을 때,
                    if((giruda == SPADE) && (owners_card_map[39]))    possibleCardIndex[numOfPossibleCards++] = 39; // 왜 스페이드2를 넣지? 제일 높은 걸 내야 하지 않나? 51?
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
            if(self->submitted_card_map[jjangCard] == -1)   break;          // 스페이드가 기루다이면
        }
    }
    else{
        for(jjangCard = 50;jjangCard>=39;jjangCard--){
            if(self->submitted_card_map[jjangCard] == -1)   break;          // why? 스페이드가 기루다 아니면
        }
    }
    if(jjangCard != 38){
        for(i=0; i<numOfPossibleCards;i++){
            if(possibleCardIndex[i] == jjangCard){
                for(j=jjangCard;j>=39;j--){                         // 스페이드2 가 39번
                    if(owners_card_map[j])  spadeJjangCard[sizeOfSpadeJjangCard++] = j;
                    else    break;
                }
                break;
            }
        }
    }
    if(self->giruda == SPADE){
        for(i=0;i<numOfPossibleCards;i++){
            if((possibleCardIndex[i] >= 39) && (possibleCardIndex[i] <= 51)){       // 스페이드2 ~ 스페이드A
                for(k=39;k<47;k++){                             // 47번 카드는 스페이드10
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
            chosenCard = [self choosingCardForTurn1:firstShape JokerShape: jkshape];    // choosingCardForTurn1 으로 이동
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

-(NSInteger) choosingCardForTurn1: (NSInteger) firstShape JokerShape:(NSInteger*) jkshape{      // 초구 프랜드일 경우를 따로 따지는 게 좋을 듯 -> 구현하기 @@@
    NSInteger chosenCard = 0;
    NSInteger starterCard = 0;
    
    if(self->isDeclarer){                           // 주공
        if(sizeOfCloverJjangCard){                  // 카드 갯수 등의 상황에 따라 어떤 모양의 짱카드를 낼지 조금 달라질 수 있지만, 일단 1턴에는 선을 먹는 게 중요하므로 짱카드이기만 하면 상관없을듯?
            chosenCard = cloverJjangCard[0];        // 기루다가 클로버이면 클로버 짱카가 있어도 낼 수 없을 텐데 시뮬 돌려보면 이상이 없음. 어디서 따지는지 코드 확인하기
        }                                           // 만약 firstShape를 통해 기루다 정보가 들어오고 기루다 모양의 짱카드,물카드 사이즈는 0으로 취급이 되는 거면 상관없는데 확인 필요 @@@
        else if(sizeOfHeartJjangCard){
            chosenCard = heartJjangCard[0];
        }
        else if(sizeOfDiamondJjangCard){            // 짱카드엔 마이티 카드 미포함이라 이대로 괜찮음
            chosenCard = diamondJjangCard[0];
        }
        else if(sizeOfSpadeJjangCard){
            chosenCard = spadeJjangCard[0];
        }
        else if(sizeOfHeartMoolCard){               // 짱카드를 못 낸다면 물카드를 내서 프랜드가 자신을 돕기를 기대함  // 프렌드 선언 부분과의 연계를 통해 수정 가능성 있음
            chosenCard = heartMoolCard[0];          // 기루다 아닌 특정 모양의 짱카를 프랜드로 지정했다면 해당 모양의 물카드를 내도록 추가 구현하기.
        }                                           // 프렌드가 마이티가 없다면 짱카를 낼 수 있게, 주공이 해당 짱카와 같은 모양의 물카드를 내도록 추가 구현하기.
        else if(sizeOfCloverMoolCard) {
            chosenCard = cloverMoolCard[0];
        }
        else if(sizeOfDiamondMoolCard){
            chosenCard = diamondMoolCard[0];
        }
        else if(sizeOfSpadeMoolCard){
            chosenCard = spadeMoolCard[0];
        }
        else if((self->giruda == SPADE) && is_there_Red_Mighty){            // 짱카드에 마이티 미포함이므로 따로 빼줌
            chosenCard = 38;            // 다이아 A
        }
        else if((self->giruda != SPADE) && is_there_Mighty){                // 짱카드에 마이티 미포함이므로 따로 빼줌
            chosenCard = 51;            // 스페이드 A
        }
        else if(owners_card_map[52]){   // 이 기루다 털이 코드는 아래 else 코드보다 밑에 있는 게 나을 듯?? 애매한 포인트카드 내는 주공을 보고 선을 먹으려는 야당의 포인트카드를 많이 유도할 수 있으니까?
            chosenCard = 52;            // 조커
            *jkshape = self->giruda;    // 주공의 기루다 털이 // 프렌드가 마이티 내지 않는 이상 선 뺏길 확률 높음 // 기루다 말고, 짱카드 만들기 목적으로 다른 모양 털이를 하는 경우도 보통 존재?
        }
        else{
            if((self->giruda == SPADE) && (self->chingooCard == (0x100+38))){        // 기루다가 스페이드 & 마이티(다이아A) 프렌드 일 경우
                if(sizeOfDiamondPointCard){
                    chosenCard = diamondPointCard[0];                                // 안심하고 다이아 포인트카드 제출
                }
            }
            else if((self->giruda != SPADE) && (self->chingooCard == (0x100+51))){   // 기루다가 스페이드가 아니고 & 마이티(스페이드A) 프렌드 일 경우
                if(sizeOfSpadePointCard){
                    chosenCard = spadePointCard[0];                                  // 안심하고 스페이드 포인트카드 제출
                }
            }
            else{                                                                    // 여기선 스->다->하->클 순으로 따지는데, 모양별 우선순위가 중요한 경우에는 후에 수정할 것.
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
                else{                                                           // 기루다 모양이 안 되는 걸 위에서 다 처리한다면, 이건 기루다 모양만 있을 시에 기루다 모양을 내는 코드??
                    chosenCard = possibleCardIndex[arc4random() % numOfPossibleCards];
                }
            }
        }
    }
    else if(isFriend){                              // 프랜드  // 주공 제외 자기보다 먼저 낸 야당(position 넘버로 구해야 하나? submittedCardinEveryTurn으로 구해야 하나?)이 낸 카드가 높은데 자신이 그걸 이길 카드가 없다면 포인트카드 아닌 물카드 내기 // 카드를 더 효율적으로 아끼려면 프랜드의 순서가 몇 번째인지까지 따져야 됨. 주공이 물카를 냈더라도 앞서 낸 야당의 카드들을 이길 수 있는 카드가 많다면 굳이 짱카를 안 내고도 이길 수 있으니까. 전체 짱카 말고 한 턴에 제출된 카드만을 가지고 짱카를 다루는 것도 괜찮을 듯. // 프랜드는 자기가 마지막 순서가 아닌 이상, 몇 번째 순서이냐에 상관없이 주공이 짱카가 아닌 카드를 냈을 때 자기가 그것보다 높은 카드들이 있다면 그중 최고패를 내고(이것마저 카드가 구리다면 선 뺏길 가능성 있음), 그것보다 높은 카드가 없다면 포기하고 포인트 카드가 아닌 제일 낮은 물패를 내라! 만약, 프랜드가 1턴의 마지막 순서라면 낼 수 있는 카드의 숫자를 최대한 아낄 수 있다.
        starterCard = self->submittedCardinEveryTurn[self->declarer];
        if((starterCard == 51) || ((self->giruda != SPADE) && (starterCard == 50))){ // 스페이드A, 스페이드K  -> 이길 게 거의 확실함 // 스페이드 기루다 && 주공이 올기루다일 경우도 해당
            if(sizeOfSpadePointCard){                       // 포인트 카드를 낮은 것부터 내서 득점을 돕는다.
                chosenCard = spadePointCard[0];             //포인트 카드를 무조건 내는 게 과연 좋을까? spadePointCard[0] 가 주공이 낸 카드 다음 짱카라면 아깝지 않나?
            }
            else if(sizeOfSpadeMoolCard){                   // 득점을 돕지 못할 바에는 물카드를 냄
                chosenCard = spadeMoolCard[0];
            }
            else if(is_there_Mighty){                       // 스페이드 포카 + 스페이드 물카 = 갖고 있는 스페이드 갯수 + 마이티 카드
                chosenCard = 51;                            // 선이 낸 모양과 같은 모양의 카드가 마이티밖에 안 남았을 경우라 어쩔 수 없이 냄
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
                if(possibleCardIndex[0] == 52){                             // 위의 경우 다 제외하고 가능한 카드의 첫번째가 조커일 경우
                    chosenCard = possibleCardIndex[1];                      // 조커 제외 다른 카드를 냄 -> 코드 보완 필요
                }
                else{
                    chosenCard = possibleCardIndex[0];                      // 조커를 냄
                }
            }
        }
        else if((starterCard == 38) || ((self->giruda == SPADE) && (starterCard == 37))){       // "다이아 A" or "스페이드 기루다일 때, 마이티 제외하고 제일 높은 다이아 K"
            if(sizeOfDiamondPointCard){
                chosenCard = diamondPointCard[0];                       // 갖고 있는 포인트카드 중 제일 낮은 게 배열 [0]에 들어감.
            }
            else if(sizeOfDiamondMoolCard){
                chosenCard = diamondMoolCard[0];
            }\
            else if(is_there_Red_Mighty){
                chosenCard = 38;
            }
            else if((self->giruda != SPADE) && (sizeOfSpadePointCard != 0)){    // 주공이 낸 모양의 카드가 없고 & 주공이 이길 게 확실해보일 때에 기루다 카드를 내는 건 낭비. 특수 케이스로 마이티가 야당에 있어서 주공이 선을 뺏길 거 같다 하더라도 기루다 내는 건 무의미. 그냥 포인트카드로 득점을 돕자.   // 모양 우선순위는 나중에 생각하기.
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
        else if(starterCard == 25){       // 하트 A
            if (sizeOfHeartPointCard){
                chosenCard = heartPointCard[0];
            }
            else if(sizeOfHeartMoolCard){
                chosenCard = heartMoolCard[0];
            }
            else if((self->giruda != SPADE) && (sizeOfSpadePointCard != 0)){
                chosenCard = spadePointCard[0];
            }
            else if((self->giruda != DIAMOND) && (sizeOfDiamondPointCard != 0)){
                chosenCard = diamondPointCard[0];
            }
            else if((self->giruda != CLOVER) && (sizeOfCloverPointCard != 0)){
                chosenCard = cloverPointCard[0];
            }
            else if((self->giruda != SPADE) && (sizeOfSpadeMoolCard != 0)){
                chosenCard = spadeMoolCard[0];
            }
            else if((self->giruda != DIAMOND) && (sizeOfDiamondMoolCard != 0)){
                chosenCard = diamondMoolCard[0];
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
        else if(starterCard == 12){       // 클로버 A
            if (sizeOfCloverPointCard){
                chosenCard = cloverPointCard[0];
            }
            else if(sizeOfCloverMoolCard){
                chosenCard = cloverMoolCard[0];
            }
            else if((self->giruda != SPADE) && (sizeOfSpadePointCard != 0)){
                chosenCard = spadePointCard[0];
            }
            else if((self->giruda != DIAMOND) && (sizeOfDiamondPointCard != 0)){
                chosenCard = diamondPointCard[0];
            }
            else if((self->giruda != HEART) && (sizeOfHeartPointCard != 0)){
                chosenCard = heartPointCard[0];
            }
            else if((self->giruda != SPADE) && (sizeOfSpadeMoolCard != 0)){
                chosenCard = spadeMoolCard[0];
            }
            else if((self->giruda != DIAMOND) && (sizeOfDiamondMoolCard != 0)){
                chosenCard = diamondMoolCard[0];
            }
            else if((self->giruda != HEART) && (sizeOfHeartMoolCard != 0)){
                chosenCard = heartMoolCard[0];
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
        else if(starterCard == spadeMoolCard[0]) {
                if(is_there_Mighty){
                    chosenCard = 51;
                }
                else if(sizeOfSpadeJjangCard){                                          // 내 앞 순서의 야당이 이미 초간을 쳤거나 마이티를 냈다면 짱카드x. 물카드 내도록 추가 구현하기 @@
                    chosenCard = spadeJjangCard[0];
                }
                else if(sizeOfSpadePointCard) {
                    chosenCard = spadePointCard[sizeOfSpadePointCard-1];                // 앞서 야당이 낸 카드가 이 카드보다 높다면 물카드를 내도록 추가 구현하기 @@
                }
                else if(sizeOfSpadeMoolCard){
                    chosenCard = spadeMoolCard[0];
                }
                else if((self->giruda == DIAMOND) && (sizeOfDiamondMoolCard != 0)){     // + 초간이라도, 야당도 기루다 카드 내서 간 칠 가능성 따지면 더 높은 카드 내야 할 수 있음 @@
                    chosenCard = diamondMoolCard[0];
                }
                else if((self->giruda == HEART) && (sizeOfHeartMoolCard != 0)){
                    chosenCard = heartMoolCard[0];
                }
                else if((self->giruda == CLOVER) && (sizeOfCloverMoolCard != 0)){
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
        else if(starterCard == diamondMoolCard[0]) {
            if(is_there_Red_Mighty){
                chosenCard = 38;
            }
            else if(sizeOfDiamondJjangCard){
                chosenCard = diamondJjangCard[0];
            }
            else if(sizeOfDiamondPointCard) {
                chosenCard = diamondPointCard[sizeOfDiamondPointCard-1];                // 앞서 야당이 낸 카드가 이 카드보다 높다면 물카드를 내도록 추가 구현하기 @@
            }
            else if(sizeOfDiamondMoolCard){
                chosenCard = diamondMoolCard[0];
            }
            else if((self->giruda == SPADE) && (sizeOfSpadeMoolCard != 0)){             // + 초간이라도 야당이 기루다 카드 낼 가능성 따지면 더 높은 카드 내야 함
                chosenCard = spadeMoolCard[0];
            }
            else if((self->giruda == HEART) && (sizeOfHeartMoolCard != 0)){
                chosenCard = heartMoolCard[0];
            }
            else if((self->giruda == CLOVER) && (sizeOfCloverMoolCard != 0)){
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
        else if(starterCard == heartMoolCard[0]) {
            if((self->giruda != SPADE) && is_there_Mighty){
                chosenCard = 51;
            }
            else if((self->giruda == SPADE) && is_there_Red_Mighty){
                chosenCard = 38;
            }
            else if(sizeOfHeartJjangCard){
                chosenCard = heartJjangCard[0];
            }
            else if(sizeOfHeartPointCard) {
                chosenCard = heartPointCard[sizeOfHeartPointCard-1];                    // 앞서 야당이 낸 카드가 이 카드보다 높다면 물카드를 내도록 추가 구현하기 @@
            }
            else if(sizeOfHeartMoolCard){
                chosenCard = heartMoolCard[0];
            }
            else if((self->giruda == SPADE) && (sizeOfSpadeMoolCard != 0)){             // + 초간이라도 야당이 기루다 카드 낼 가능성 따지면 더 높은 카드 내야 함
                chosenCard = spadeMoolCard[0];
            }
            else if((self->giruda == DIAMOND) && (sizeOfDiamondMoolCard != 0)){
                chosenCard = diamondMoolCard[0];
            }
            else if((self->giruda == CLOVER) && (sizeOfCloverMoolCard != 0)){
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
        else if(starterCard == cloverMoolCard[0]) {
            if((self->giruda != SPADE) && is_there_Mighty){
                chosenCard = 51;
            }
            else if((self->giruda == SPADE) && is_there_Red_Mighty){
                chosenCard = 38;
            }
            else if(sizeOfCloverJjangCard){
                chosenCard = cloverJjangCard[0];
            }
            else if(sizeOfCloverPointCard) {
                chosenCard = cloverPointCard[sizeOfCloverPointCard-1];                  // 앞서 야당이 낸 카드가 이 카드보다 높다면 물카드를 내도록 추가 구현하기 @@
            }
            else if(sizeOfCloverMoolCard){
                chosenCard = cloverMoolCard[0];
            }
            else if((self->giruda == SPADE) && (sizeOfSpadeMoolCard != 0)){             // + 초간이라도 야당이 기루다 카드 낼 가능성 따지면 더 높은 카드 내야 함
                chosenCard = spadeMoolCard[0];
            }
            else if((self->giruda == DIAMOND) && (sizeOfDiamondMoolCard != 0)){
                chosenCard = diamondMoolCard[0];
            }
            else if((self->giruda == HEART) && (sizeOfHeartMoolCard != 0)){
                chosenCard = heartMoolCard[0];
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
        else
            chosenCard = possibleCardIndex[arc4random() % numOfPossibleCards]; // 추가 구현 필요 @@ // (1) 주공이 조커를 냈을 때 -> 물카랑 비슷하게 구현. 짱카나 마이티가 없다면 물카를 내라? (2) 주공이 짱카 아닌 포카 냈을 때 대응 -> 짱카 있으면 짱카 내고 없으면 마이티 카드 내기. 기루다 낼 수 있으면 기루다. 그것도 없으면 ~~
            //if(chosenCard == 52){
            //    switch(arc4random() % 4){          // 프랜드는 1턴에서 선이 될 수 없는데 왜 조커 냈다고 모양 선택해? -> 이 부분 일단 지워야 할 듯
            //        case 0:
            //            *jkshape = CLOVER;
            //            break;
            //        case 1:
            //            *jkshape = HEART;
            //            break;
            //        case 2:
            //            *jkshape = DIAMOND;
            //            break;
            //        case 3:
            //            *jkshape = SPADE;
            //            break;
            //    }
            //}
    }
    else{   // 야당      // 야당이 마이티 카드 갖고 있을 확률은 거의 없지만, 마이티 갖고 있을 상황도 제시해야 함. 예를 들어 노기루다일 때는 낼 게 제일 구린 카드 아니면 마이티
        starterCard = self->submittedCardinEveryTurn[self->declarer];
        if(((starterCard >= 0) && (starterCard <= 12)) || ((starterCard == 52) && (*jkshape == CLOVER))){      // 앞선 사람들이 어떤 카드를 냈냐에 따라 같은 모양 물카를 낼지 같은 모양 포인트 카드를 낼지 달라짐 -> 아직 완벽히 구현은 x
            if(sizeOfCloverJjangCard){                              // 기루다에 따라서 possibleCard에 A 카드가 들어가지 못한다면 코드 수정 필요 **
                chosenCard = cloverJjangCard[0];                    // 앞선 순서의 프렌드가 기루다를 내서 초간을 치거나 마이티를 냈을 경우엔 짱카드 내는 건 손해 -> 추가 구현 필요
            }
            else if(sizeOfCloverPointCard){
                if(cloverPointCard[sizeOfCloverPointCard-1] > starterCard){  // 선이 낸 카드보다 큰 것들 중 제일 작은 걸 선택하는 건 얘가 한 턴 내의 마지막 순서가 아닌 이상 위험해서 일단 제일 큰 걸로 선택했는데 나중에 수정해도 될 듯
                    chosenCard = cloverPointCard[sizeOfCloverPointCard-1];
                }
                else if(sizeOfCloverMoolCard){
                    chosenCard = cloverMoolCard[0];
                }
            }
            else if(sizeOfCloverMoolCard){
                chosenCard = cloverMoolCard[0];
            }
            else if(self->giruda == CLOVER){ // 초간 치는 경우인데, 기루다 카드 중에서도 높은 걸 내야할지 낮은 걸 내야할지 정하는 선도 경우에 따라 다르기 때문에 추가 구현 필요. 지금은 물카드만 해놓음.
                if(sizeOfCloverMoolCard){
                    chosenCard = cloverMoolCard[0];
                }
            }
            else if(self->giruda == HEART){
                if(sizeOfHeartMoolCard){
                    chosenCard = heartMoolCard[0];
                }
            }
            else if(self->giruda == DIAMOND){
                if(sizeOfDiamondMoolCard){
                    chosenCard = diamondMoolCard[0];
                }
            }
            else if(self->giruda == SPADE){
                if(sizeOfHeartMoolCard){
                    chosenCard = heartMoolCard[0];
                }
            }
        }
        else if(((starterCard >= 13) && (starterCard <= 25)) || ((starterCard == 52) && (*jkshape == HEART))){
            if(sizeOfHeartJjangCard){
                chosenCard = heartJjangCard[0];
            }
            else if(sizeOfHeartPointCard){
                if(heartPointCard[sizeOfHeartPointCard-1] > starterCard){
                    chosenCard = heartPointCard[sizeOfHeartPointCard-1];
                }
                else if(sizeOfHeartMoolCard){
                    chosenCard = heartMoolCard[0];
                }
            }
            else if(sizeOfHeartMoolCard){
                chosenCard = heartMoolCard[0];
            }
            else if(self->giruda == CLOVER){        //
                if(sizeOfCloverMoolCard){
                    chosenCard = cloverMoolCard[0];
                }
            }
            else if(self->giruda == HEART){
                if(sizeOfHeartMoolCard){
                    chosenCard = heartMoolCard[0];
                }
            }
            else if(self->giruda == DIAMOND){
                if(sizeOfDiamondMoolCard){
                    chosenCard = diamondMoolCard[0];
                }
            }
            else if(self->giruda == SPADE){
                if(sizeOfHeartMoolCard){
                    chosenCard = heartMoolCard[0];
                }
            }
        }
        else if(((starterCard >= 26) && (starterCard <= 38)) || ((starterCard == 52) && (*jkshape == DIAMOND))){
            if(sizeOfDiamondJjangCard){
                chosenCard = diamondJjangCard[0];
            }
            else if(sizeOfDiamondPointCard){
                if(diamondPointCard[sizeOfDiamondPointCard-1] > starterCard){
                    chosenCard = diamondPointCard[sizeOfDiamondPointCard-1];
                }
                else if(sizeOfDiamondMoolCard){
                    chosenCard = diamondMoolCard[0];
                }
            }
            else if(sizeOfDiamondMoolCard){
                chosenCard = diamondMoolCard[0];
            }
            else if(self->giruda == CLOVER){        //
                if(sizeOfCloverMoolCard){
                    chosenCard = cloverMoolCard[0];
                }
            }
            else if(self->giruda == HEART){
                if(sizeOfHeartMoolCard){
                    chosenCard = heartMoolCard[0];
                }
            }
            else if(self->giruda == DIAMOND){
                if(sizeOfDiamondMoolCard){
                    chosenCard = diamondMoolCard[0];
                }
            }
            else if(self->giruda == SPADE){
                if(sizeOfHeartMoolCard){
                    chosenCard = heartMoolCard[0];
                }
            }
        }
        else if(((starterCard >= 39) && (starterCard <= 51)) || ((starterCard == 52) && (*jkshape == SPADE))){
            if(sizeOfSpadeJjangCard){
                chosenCard = spadeJjangCard[0];
            }
            else if(sizeOfSpadePointCard){
                if(spadePointCard[sizeOfSpadePointCard-1] > starterCard){
                    chosenCard = spadePointCard[sizeOfSpadePointCard-1];
                }
                else if(sizeOfSpadeMoolCard){
                    chosenCard = spadeMoolCard[0];
                }
            }
            else if(sizeOfSpadeMoolCard){
                chosenCard = spadeMoolCard[0];
            }
            else if(self->giruda == CLOVER){        //
                if(sizeOfCloverMoolCard){
                    chosenCard = cloverMoolCard[0];
                }
            }
            else if(self->giruda == HEART){
                if(sizeOfHeartMoolCard){
                    chosenCard = heartMoolCard[0];
                }
            }
            else if(self->giruda == DIAMOND){
                if(sizeOfDiamondMoolCard){
                    chosenCard = diamondMoolCard[0];
                }
            }
            else if(self->giruda == SPADE){
                if(sizeOfHeartMoolCard){
                    chosenCard = heartMoolCard[0];
                }
            }
            else if(self->giruda == 0x60){   // 노기루다일 경우 (0x60 인식 안 되면 그냥 else 로 바꾸기)
            }
        }
        else
            chosenCard = possibleCardIndex[arc4random() % numOfPossibleCards];  // 임시로 씀. 제일 낮은 물카드를 내는 게 좋을 거 같은데 물카드 중 어떤 모양을 택할지는 갯수 등의 영향이 있음. 세부적인 건 나중에
        
        //chosenCard = possibleCardIndex[arc4random() % numOfPossibleCards];
        //if(chosenCard == 52){
        //    switch(arc4random() % 4){               // 야당은 1턴에서 선이 될 수 없는데 왜 조커 냈다고 모양 선택해? -> 이 부분 일단 지워야 할 듯
        //        case 0:
        //            *jkshape = CLOVER;
        //            break;
        //        case 1:
        //            *jkshape = HEART;
        //            break;
        //        case 2:
        //            *jkshape = DIAMOND;
        //            break;
        //        case 3:
        //            *jkshape = SPADE;
        //            break;
        //    }
        //}
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

-(declaration_t) declaring_AI:(NSInteger) pastGoal{         // 특정 카드가 있을 때 무조건 선언 갯수 올리면, 특정 카드들이 얼마나 있는지 예측이 쉬워질 위험성 존재 -> 확률 설정까지 해주는 게 낫나?
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
        if(tmpGoal > pastGoal){
            gapGoal = tmpGoal - pastGoal;
            decision.shp = SPADE;
            decision.goal = pastGoal + arc4random()%gapGoal + 1;
            return decision;
        }
    }
    if(num_of_diamond >= 4){
        tmpGoal = num_of_diamond+ 9;
        if(is_there_Mighty) tmpGoal++;
        if(is_there_Joker) tmpGoal++;
        if(tmpGoal > pastGoal){
            gapGoal = tmpGoal - pastGoal;
            decision.shp = DIAMOND;
            decision.goal = pastGoal + arc4random()%gapGoal + 1;
            return decision;
        }
    }
    if(num_of_heart >= 4){
        tmpGoal = num_of_heart + 9;
        if(is_there_Mighty) tmpGoal++;
        if(is_there_Joker) tmpGoal++;
        if(tmpGoal > pastGoal){
            gapGoal = tmpGoal - pastGoal;
            decision.shp = HEART;
            decision.goal = pastGoal + arc4random()%gapGoal + 1;
            return decision;
        }
    }
    if(num_of_clover >= 4){
        tmpGoal = num_of_clover + 9;
        if(is_there_Mighty) tmpGoal++;
        if(is_there_Joker) tmpGoal++;
        if(tmpGoal > pastGoal){
            gapGoal = tmpGoal - pastGoal;
            decision.shp = CLOVER;
            decision.goal = pastGoal + arc4random()%gapGoal + 1;
            return decision;
        }
    }
    decision.shp = -1;
    decision.goal = -1;
    return decision;        // 노기루다 ?
}

@end
