#import "soloGameViewController.h"

@interface soloGameViewController ()

@end

@implementation soloGameViewController
@synthesize borderView;
@synthesize biddingView;
@synthesize girudaButtons;
@synthesize pledgeButtons;
@synthesize biddingOKButton;
@synthesize biddingPatoButton;
@synthesize biddingPassButton;
@synthesize passLabel;
@synthesize patoLabel;
@synthesize declaringLabel;
@synthesize declarationResultLabel;
@synthesize selectingThreeCardsView;
@synthesize selectingThreeCardsLabel;
@synthesize discardingThreeCardsButton;
@synthesize selectingFriendView;
@synthesize selectingFriendLabel;
@synthesize friendConfirmButton;
@synthesize mightyFriendButton;
@synthesize jokerFriendButton;
@synthesize girudaAFriendButton;
@synthesize chogooFriendButton;
@synthesize noFriendButton;
@synthesize playerOneFriend;
@synthesize playerTwoFriend;
@synthesize playerThreeFriend;
@synthesize playerFourFriend;
@synthesize confirmingFriendView;
@synthesize confirmingFriendLabel;
@synthesize userSelectingCardView;
@synthesize userSubmittingCardButton;
@synthesize jokerSpadeCallButton;
@synthesize jokerDiamondCallButton;
@synthesize jokerHeartCallButton;
@synthesize jokerCloverCallButton;
@synthesize jokerAnyCallButton;
@synthesize jokercallOKButton;
@synthesize jokercallCancelButton;
@synthesize jokerShapeView;
@synthesize jokerShapeLabel;
@synthesize declarerInfoView;
@synthesize girudaAtDeclarerInfo;
@synthesize pledgeAtDeclarerInfo;
@synthesize friendAtDeclarerInfo;
@synthesize cardBackVerticalImage;
@synthesize cardBackHorizontalImage;

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self.view setBackgroundColor:[UIColor colorWithRed:0.1f green:0.508f blue:0.1f alpha:1.0f]];
    [NSTimer scheduledTimerWithTimeInterval:0.0f target:self selector:@selector(start) userInfo:nil repeats:NO];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void) start{
    NSInteger i = 0;
    //Initialization
    [self viewInitializaion];
    mightyGame = [[Game alloc] init];
    self->dealer = -1;
    //Show Basic Objects
    
    //Stage Add Player
    
    [mightyGame addPlayer:[[PlayerInfo alloc] initWithID:@"Me" andSerial:1024]];
    [mightyGame addPlayer:[[PlayerInfo alloc] initAsComputer:@"Steve" andPosition:1]];
    [mightyGame addPlayer:[[PlayerInfo alloc] initAsComputer:@"Bill" andPosition:2]];
    [mightyGame addPlayer:[[PlayerInfo alloc] initAsComputer:@"Jack" andPosition:3]];
    [mightyGame addPlayer:[[PlayerInfo alloc] initAsComputer:@"Mark" andPosition:4]];
    
    NSLog(@"%lf, %lf",nameTagLabelWidth, nameTagLabelHeight);
    for(i=0;i<5;i++){
        [nameTagView[i] setText:[NSString stringWithString:[[mightyGame player:i] identifier]]];
        [self.view addSubview:nameTagView[i]];
    }
    [NSTimer scheduledTimerWithTimeInterval:0.0f target:self selector:@selector(startAndShuffle) userInfo:nil repeats:NO];
}

-(void) startAndShuffle{
    NSInteger i=0;
    pledge = 13;
    //Shuffle and Deal
    [self.view addSubview:deckBackView];
    
    if(self->dealer == -1){
        [mightyGame setDealer:self->dealer = arc4random() % 5];
    }
    else{
        [mightyGame setDealer:self->dealer];
    }
    [mightyGame shuffle];
    [mightyGame deal];
    for(i=0;i<10;i++)   self->playerCard[i] = [[mightyGame player:0] getCards:i];
    self->dealingCount = 0;
    [mightyGame showPlayerscards];
    [NSTimer scheduledTimerWithTimeInterval:0.0f target:self selector:@selector(deal) userInfo:nil repeats:NO];
}

-(void) deal{ //움직이는 애니메이션 부분
    NSInteger numOfGivenCards = dealingCount & 3;
    switch(self->dealer){
        case 0:
            switch(dealingCount % 5){
                case 0:case 3:
                    [self->dealedCardBackView setImage:[self->cardBackHorizontalImage objectAtIndex:numOfGivenCards]];
                    [self->dealedCardBackView setFrame:cardStartingPointHorizontal[numOfGivenCards]];
                    break;
                case 1:case 2:case 4:
                    [self->dealedCardBackView setImage:[self->cardBackVerticalImage objectAtIndex:numOfGivenCards]];
                    [self->dealedCardBackView setFrame:cardStartingPointVirtical[numOfGivenCards]];
                    break;
            }
            break;
        case 1:
            switch(dealingCount % 5){
                case 2:case 4:
                    [self->dealedCardBackView setImage:[self->cardBackHorizontalImage objectAtIndex:numOfGivenCards]];
                    [self->dealedCardBackView setFrame:cardStartingPointHorizontal[numOfGivenCards]];
                    break;
                case 0:case 1:case 3:
                    [self->dealedCardBackView setImage:[self->cardBackVerticalImage objectAtIndex:numOfGivenCards]];
                    [self->dealedCardBackView setFrame:cardStartingPointVirtical[numOfGivenCards]];
                    break;
            }
            break;
        case 2:
            switch(dealingCount % 5){
                case 1:case 3:
                    [self->dealedCardBackView setImage:[self->cardBackHorizontalImage objectAtIndex:numOfGivenCards]];
                    [self->dealedCardBackView setFrame:cardStartingPointHorizontal[numOfGivenCards]];
                    break;
                case 0:case 2:case 4:
                    [self->dealedCardBackView setImage:[self->cardBackVerticalImage objectAtIndex:numOfGivenCards]];
                    [self->dealedCardBackView setFrame:cardStartingPointVirtical[numOfGivenCards]];
                    break;
            }
            break;
        case 3:
            switch(dealingCount % 5){
                case 0:case 2:
                    [self->dealedCardBackView setImage:[self->cardBackHorizontalImage objectAtIndex:numOfGivenCards]];
                    [self->dealedCardBackView setFrame:cardStartingPointHorizontal[numOfGivenCards]];
                    break;
                case 1:case 3:case 4:
                    [self->dealedCardBackView setImage:[self->cardBackVerticalImage objectAtIndex:numOfGivenCards]];
                    [self->dealedCardBackView setFrame:cardStartingPointVirtical[numOfGivenCards]];
                    break;
            }
            break;
        case 4:
            switch(dealingCount % 5){
                case 1:case 4:
                    [self->dealedCardBackView setImage:[self->cardBackHorizontalImage objectAtIndex:numOfGivenCards]];
                    [self->dealedCardBackView setFrame:cardStartingPointHorizontal[numOfGivenCards]];
                    break;
                case 0:case 2:case 3:
                    [self->dealedCardBackView setImage:[self->cardBackVerticalImage objectAtIndex:numOfGivenCards]];
                    [self->dealedCardBackView setFrame:cardStartingPointVirtical[numOfGivenCards]];
                    break;
            }
            break;
    }
    [self.view addSubview:self->dealedCardBackView];
    [UIView beginAnimations:nil context:NULL];
    [UIView setAnimationDuration:0.3]; //카드 움직이는 시간
    [UIView setAnimationDelegate:self];
    [UIView setAnimationRepeatAutoreverses:NO];
    [UIView setAnimationCurve:UIViewAnimationCurveLinear];
    [self->dealedCardBackView setFrame:cardDestination[(dealingCount+self->dealer+1)%5][dealingCount&3]];
    [UIView setAnimationDidStopSelector:@selector(removeCardBack)]; //애니메이션끝나면
    [UIView commitAnimations];
}

-(void) removeCardBack{
    [self->dealedCardBackView removeFromSuperview];
    [NSTimer scheduledTimerWithTimeInterval:0.0f target:self selector:@selector(showGivenDecks) userInfo:nil repeats:NO];
}

-(void) showGivenDecks{ //쌓이는거
    switch(self->dealingCount){
        case 0:
            [self.view addSubview:self->cardBackView[(self->dealer * 10) % 50]];
            break;
        case 1:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 10) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 11) % 50]];
            break;
        case 2:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 20) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 21) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 22) % 50]];
            break;
        case 3:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 30) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 31) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 32) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 33) % 50]];
            break;
        case 4:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 40) % 50]];
            break;
        case 5:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 1) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 2) % 50]];
            break;
        case 6:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 12) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 13) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 14) % 50]];
            break;
        case 7:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 23) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 24) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 25) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 26) % 50]];
            break;
        case 8:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 34) % 50]];
            break;
        case 9:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 41) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 42) % 50]];
            break;
        case 10:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 3) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 4) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 5) % 50]];
            break;
        case 11:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 15) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 16) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 17) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 18) % 50]];
            break;
        case 12:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 27) % 50]];
            break;
        case 13:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 35) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 36) % 50]];
            break;
        case 14:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 43) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 44) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 45) % 50]];
            break;
        case 15:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 6) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 7) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 8) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 9) % 50]];
            break;
        case 16:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 19) % 50]];
            break;
        case 17:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 28) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 29) % 50]];
            break;
        case 18:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 37) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 38) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 39) % 50]];
            break;
        case 19:
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 46) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 47) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 48) % 50]];
            [self.view addSubview:self->cardBackView[(self->dealer * 10 + 49) % 50]];
            break;
    }
    if(self->dealingCount++ == 19){
        [NSTimer scheduledTimerWithTimeInterval:0.5 target:self selector:@selector(showUsersCards) userInfo:nil repeats:NO];
    }
    else{
        [NSTimer scheduledTimerWithTimeInterval:0.0f target:self selector:@selector(deal) userInfo:nil repeats:NO];
    }
}

-(void) showUsersCards{ //카드 보여주는 부분
    NSInteger i=0;
    for(i=0;i<10;i++){
        [self->cardBackView[i+40] removeFromSuperview];
    }
    for(i=0;i<10;i++){
        [playingCardView[playerCard[i]] setFrame:CGRectMake(userCardXPosition[0] + (CGFloat)i * cardColSpace, userCardYPosition[0], cardWidth, cardHeight)];
        [self.view addSubview:playingCardView[playerCard[i]]];
    }
    
    [NSTimer scheduledTimerWithTimeInterval:0.5 target:self selector:@selector(bidding) userInfo:nil repeats:NO];
    return;
}

-(void) bidding{ // 공약 정하기
    for(NSInteger i=0;i<5;i++){
        self->isPassed[i] = 100;
    }
    self->biddingCount = self->dealer;
    if(self->biddingCount == 0){
        [self userBidding];
    }
    else{
        [self playerBidding];
    }
}

-(void) userBidding{ //내 공약
    const CGFloat biddingViewWidth = viewWidth*0.6f;
    const CGFloat biddingViewHeight = viewHeight*0.4f;
    biddingOKButton = [[UIButton alloc] initWithFrame:CGRectMake(biddingViewWidth*0.7f, biddingViewHeight*0.05f, biddingViewWidth*0.2f, biddingViewHeight*0.1f)];
    biddingPatoButton = [[UIButton alloc] initWithFrame:CGRectMake(biddingViewWidth*0.4f, biddingViewHeight*0.05f, biddingViewWidth*0.2f, biddingViewHeight*0.1f)];
    biddingPassButton = [[UIButton alloc] initWithFrame:CGRectMake(biddingViewWidth*0.1f, biddingViewHeight*0.05f, biddingViewWidth*0.2f, biddingViewHeight*0.1f)];
    self->girudaButtons = [[NSArray alloc] initWithObjects:
                           [[UIButton alloc] initWithFrame:CGRectMake(biddingViewWidth*0.1f,biddingViewHeight*0.2f,biddingViewWidth*0.38f,biddingViewHeight*0.12f)],
                           [[UIButton alloc] initWithFrame:CGRectMake(biddingViewWidth*0.1f,biddingViewHeight*0.33f,biddingViewWidth*0.38f,biddingViewHeight*0.12f)],
                           [[UIButton alloc] initWithFrame:CGRectMake(biddingViewWidth*0.1f,biddingViewHeight*0.46f,biddingViewWidth*0.38f,biddingViewHeight*0.12f)],
                           [[UIButton alloc] initWithFrame:CGRectMake(biddingViewWidth*0.1f,biddingViewHeight*0.59f,biddingViewWidth*0.38f,biddingViewHeight*0.12f)],
                           [[UIButton alloc] initWithFrame:CGRectMake(biddingViewWidth*0.1f,biddingViewHeight*0.72f,biddingViewWidth*0.38f,biddingViewHeight*0.12f)],
                           nil];
    self->pledgeButtons = [[NSArray alloc] initWithObjects:
                           [[UIButton alloc] initWithFrame:CGRectMake(biddingViewWidth*0.52f,biddingViewHeight*0.22f,biddingViewWidth*0.18f,biddingViewHeight*0.14f)],
                           [[UIButton alloc] initWithFrame:CGRectMake(biddingViewWidth*0.52f,biddingViewHeight*0.37f,biddingViewWidth*0.18f,biddingViewHeight*0.14f)],
                           [[UIButton alloc] initWithFrame:CGRectMake(biddingViewWidth*0.52f,biddingViewHeight*0.52f,biddingViewWidth*0.18f,biddingViewHeight*0.14f)],
                           [[UIButton alloc] initWithFrame:CGRectMake(biddingViewWidth*0.52f,biddingViewHeight*0.67f,biddingViewWidth*0.18f,biddingViewHeight*0.14f)],
                           [[UIButton alloc] initWithFrame:CGRectMake(biddingViewWidth*0.72f,biddingViewHeight*0.22f,biddingViewWidth*0.18f,biddingViewHeight*0.14f)],
                           [[UIButton alloc] initWithFrame:CGRectMake(biddingViewWidth*0.72f,biddingViewHeight*0.37f,biddingViewWidth*0.18f,biddingViewHeight*0.14f)],
                           [[UIButton alloc] initWithFrame:CGRectMake(biddingViewWidth*0.72f,biddingViewHeight*0.52f,biddingViewWidth*0.18f,biddingViewHeight*0.14f)],
                           [[UIButton alloc] initWithFrame:CGRectMake(biddingViewWidth*0.72f,biddingViewHeight*0.67f,biddingViewWidth*0.18f,biddingViewHeight*0.14f)],
                           nil];
    [(UIButton*)[self->girudaButtons objectAtIndex:0] setTitle:@"Spade" forState:UIControlStateNormal];
    [(UIButton*)[self->girudaButtons objectAtIndex:1] setTitle:@"Diamond" forState:UIControlStateNormal];
    [(UIButton*)[self->girudaButtons objectAtIndex:2] setTitle:@"Heart" forState:UIControlStateNormal];
    [(UIButton*)[self->girudaButtons objectAtIndex:3] setTitle:@"Clover" forState:UIControlStateNormal];
    [(UIButton*)[self->girudaButtons objectAtIndex:4] setTitle:@"No Giruda" forState:UIControlStateNormal];
    for(NSInteger i=0;i<5;i++){
        [(UIButton*)[self->girudaButtons objectAtIndex:i] setBackgroundColor:[UIColor colorWithRed:0.05f green:0.254f blue:0.05f alpha:1.0f]];
        [((UIButton*)[self->girudaButtons objectAtIndex:i]).layer setBorderWidth:3.0f];
        [((UIButton*)[self->girudaButtons objectAtIndex:i]).layer setBorderColor:[UIColor colorWithRed:0.5f green:0.5f blue:0.5f alpha:1.0f].CGColor];
        [(UIButton*)[self->girudaButtons objectAtIndex:i] setTitleColor:[UIColor colorWithRed:1.0f green:1.0f blue:0.3f alpha:1.0f] forState:UIControlStateNormal];
        [(UIButton*)[self->girudaButtons objectAtIndex:i] setEnabled:YES];
        [(UIButton*)[self->girudaButtons objectAtIndex:i] setTag:i+0xa00];
        [(UIButton*)[self->girudaButtons objectAtIndex:i] addTarget:self action:@selector(actionForChoosingGirudaAndPledge:) forControlEvents:UIControlEventTouchUpInside];
    }
    for(NSInteger i=0;i<8;i++){
        [(UIButton*)[self->pledgeButtons objectAtIndex:i] setTitle:[NSString stringWithFormat:@"%ld",i+13] forState:UIControlStateNormal];
        [(UIButton*)[self->pledgeButtons objectAtIndex:i] setBackgroundColor:[UIColor colorWithRed:0.05f green:0.254f blue:0.05f alpha:1.0f]];
        [((UIButton*)[self->pledgeButtons objectAtIndex:i]).layer setBorderWidth:3.0f];
        [((UIButton*)[self->pledgeButtons objectAtIndex:i]).layer setBorderColor:[UIColor colorWithRed:0.5f green:0.5f blue:0.5f alpha:1.0f].CGColor];
        [(UIButton*)[self->pledgeButtons objectAtIndex:i] setTag:i+0xb00];
        [(UIButton*)[self->pledgeButtons objectAtIndex:i] addTarget:self action:@selector(actionForChoosingGirudaAndPledge:) forControlEvents:UIControlEventTouchUpInside];
        if(pledge < (i+13)){
            [(UIButton*)[self->pledgeButtons objectAtIndex:i] setTitleColor:[UIColor colorWithRed:1.0f green:1.0f blue:0.3f alpha:1.0f] forState:UIControlStateNormal];
            [(UIButton*)[self->pledgeButtons objectAtIndex:i] setEnabled:YES];
        }
        else{
            [(UIButton*)[self->pledgeButtons objectAtIndex:i] setTitleColor:[UIColor colorWithRed:0.5f green:0.5f blue:0.5f alpha:1.0f] forState:UIControlStateNormal];
            [(UIButton*)[self->pledgeButtons objectAtIndex:i] setEnabled:NO];
        }
    }
    self->biddingView = [[UIView alloc] initWithFrame:CGRectMake(viewWidth*0.2f,viewHeight*0.3f,biddingViewWidth,biddingViewHeight)];
    [biddingView setBackgroundColor:[UIColor colorWithRed:0.05f green:0.254f blue:0.05f alpha:0.6f]];
    
    [self->biddingOKButton setTitle:@"선언" forState:UIControlStateNormal];
    [self->biddingOKButton addTarget:self action:@selector(showDeclarationLabel) forControlEvents:UIControlEventTouchUpInside];
    self->biddingOKButton.enabled = NO;
    [self->biddingPatoButton setTitle:@"파토" forState:UIControlStateNormal];
    [self->biddingPatoButton addTarget:self action:@selector(showPatoLabel) forControlEvents:UIControlEventTouchUpInside];
    
    if([[mightyGame player:0] isPato]){
        [self->biddingPatoButton setTitleColor:[UIColor yellowColor] forState:UIControlStateNormal];
        self->biddingPatoButton.enabled = YES;
    }
    else{
        [self->biddingPatoButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        self->biddingPatoButton.enabled = NO;
    }
    
    [self->biddingOKButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [self->biddingPassButton setTitle:@"패스" forState:UIControlStateNormal];
    [self->biddingPassButton addTarget:self action:@selector(showPassLabel) forControlEvents:UIControlEventTouchUpInside];
    [self->biddingPassButton setTitleColor:[UIColor redColor] forState:UIControlStateNormal];
    self->selectedPledge = 0;
    self->selectedGiruda = 0;
    
    [self.view addSubview:biddingView];
    //    [self->biddingView addSubview:self->GirudaTable];
    //    [self->biddingView addSubview:self->GoalTable];
    for(NSInteger i=0;i<5;i++){
        [self->biddingView addSubview:[self->girudaButtons objectAtIndex:i]];
    }
    for(NSInteger i=0;i<8;i++){
        [self->biddingView addSubview:[self->pledgeButtons objectAtIndex:i]];
    }
    [self->biddingView addSubview:self->biddingOKButton];
    [self->biddingView addSubview:self->biddingPatoButton];
    [self->biddingView addSubview:self->biddingPassButton];
}

-(void) actionForChoosingGirudaAndPledge:(UIButton*) button{
    NSLog(@"%ld",[button tag]);
    switch([button tag] & 0xf00){
        case 0xa00:
            for(NSInteger i=0;i<5;i++){
                [((UIButton*)[self->girudaButtons objectAtIndex:i]).layer setBorderColor:[UIColor colorWithRed:0.5f green:0.5f blue:0.5f alpha:1.0f].CGColor];
            }
            [((UIButton*)[self->girudaButtons objectAtIndex:[button tag] & 0xf]).layer setBorderColor:[UIColor colorWithRed:1.0f green:1.0f blue:0.3f alpha:1.0f].CGColor];
            switch([button tag] & 0xf){
                case 0:
                    if(self->selectedPledge == 13){
                        self->selectedPledge = 0;
                    }
                    [(UIButton*)[self->pledgeButtons objectAtIndex:0] setTitleColor:[UIColor colorWithRed:0.5f green:0.5f blue:0.5f alpha:1.0f] forState:UIControlStateNormal];
                    [(UIButton*)[self->pledgeButtons objectAtIndex:0] setEnabled:NO];
                    [((UIButton*)[self->pledgeButtons objectAtIndex:0]).layer setBorderColor:[UIColor colorWithRed:0.5f green:0.5f blue:0.5f alpha:1.0f].CGColor];
                    self->selectedGiruda = SPADE;
                    NSLog(@"Spade is selected");
                    break;
                case 1:
                    if(self->selectedPledge == 13){
                        self->selectedPledge = 0;
                    }
                    [(UIButton*)[self->pledgeButtons objectAtIndex:0] setTitleColor:[UIColor colorWithRed:0.5f green:0.5f blue:0.5f alpha:1.0f] forState:UIControlStateNormal];
                    [(UIButton*)[self->pledgeButtons objectAtIndex:0] setEnabled:NO];
                    [((UIButton*)[self->pledgeButtons objectAtIndex:0]).layer setBorderColor:[UIColor colorWithRed:0.5f green:0.5f blue:0.5f alpha:1.0f].CGColor];
                    self->selectedGiruda = DIAMOND;
                    NSLog(@"Diamond is selected");
                    break;
                case 2:
                    if(self->selectedPledge == 13){
                        self->selectedPledge = 0;
                    }
                    [(UIButton*)[self->pledgeButtons objectAtIndex:0] setTitleColor:[UIColor colorWithRed:0.5f green:0.5f blue:0.5f alpha:1.0f] forState:UIControlStateNormal];
                    [(UIButton*)[self->pledgeButtons objectAtIndex:0] setEnabled:NO];
                    [((UIButton*)[self->pledgeButtons objectAtIndex:0]).layer setBorderColor:[UIColor colorWithRed:0.5f green:0.5f blue:0.5f alpha:1.0f].CGColor];
                    self->selectedGiruda = HEART;
                    NSLog(@"Heart is selected");
                    break;
                case 3:
                    if(self->selectedPledge == 13){
                        self->selectedPledge = 0;
                    }
                    [(UIButton*)[self->pledgeButtons objectAtIndex:0] setTitleColor:[UIColor colorWithRed:0.5f green:0.5f blue:0.5f alpha:1.0f] forState:UIControlStateNormal];
                    [(UIButton*)[self->pledgeButtons objectAtIndex:0] setEnabled:NO];
                    [((UIButton*)[self->pledgeButtons objectAtIndex:0]).layer setBorderColor:[UIColor colorWithRed:0.5f green:0.5f blue:0.5f alpha:1.0f].CGColor];
                    self->selectedGiruda = CLOVER;
                    NSLog(@"Clover is selected");
                    break;
                case 4:
                    self->selectedGiruda = NOGIRUDA;
                    if(pledge == 13){
                        [(UIButton*)[self->pledgeButtons objectAtIndex:0] setTitleColor:[UIColor colorWithRed:1.0f green:1.0f blue:0.3f alpha:1.0f] forState:UIControlStateNormal];
                        [(UIButton*)[self->pledgeButtons objectAtIndex:0] setEnabled:YES];
                    }
                    NSLog(@"No giruda is selected");
                    break;
                default:
                    break;
            }
            
            if(self->selectedGiruda != 0 && self->selectedPledge != 0){
                self->biddingOKButton.enabled = YES;
                [self->biddingOKButton setTitleColor:[UIColor blueColor] forState:UIControlStateNormal];
            }
            else{
                self->biddingOKButton.enabled = NO;
                [self->biddingOKButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
            }
            break;
        case 0xb00:
            for(NSInteger i=0;i<8;i++){
                [((UIButton*)[self->pledgeButtons objectAtIndex:i]).layer setBorderColor:[UIColor colorWithRed:0.5f green:0.5f blue:0.5f alpha:1.0f].CGColor];
            }
            [((UIButton*)[self->pledgeButtons objectAtIndex:[button tag] & 0xf]).layer setBorderColor:[UIColor colorWithRed:1.0f green:1.0f blue:0.3f alpha:1.0f].CGColor];
            self->selectedPledge = ([button tag] & 0xf) + 13;
            
            if(self->selectedGiruda != 0 && self->selectedPledge != 0){
                self->biddingOKButton.enabled = YES;
                [self->biddingOKButton setTitleColor:[UIColor blueColor] forState:UIControlStateNormal];
            }
            else{
                self->biddingOKButton.enabled = NO;
                [self->biddingOKButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
            }
            break;
        case 0xc00:
            switch([button tag] & 0xf){
                case 0:
                    if([mightyGame giruda] == SPADE){
                        
                    }
                    else if([mightyGame giruda] == NOGIRUDA){
                    }
                    else{
                        
                    }
                    break;
                case 1:
                    if([mightyGame giruda] == DIAMOND){
                        
                    }
                    else if([mightyGame giruda] == NOGIRUDA){
                    }
                    else{
                        
                    }
                    break;
                case 2:
                    if([mightyGame giruda] == HEART){
                        
                    }
                    else if([mightyGame giruda] == NOGIRUDA){
                    }
                    else{
                        
                    }
                    break;
                case 3:
                    if([mightyGame giruda] == CLOVER){
                        
                    }
                    else if([mightyGame giruda] == NOGIRUDA){
                    }
                    else{
                        
                    }
                    break;
                case 4:
                    if([mightyGame giruda] == NOGIRUDA){
                        
                    }
                    else{
                        
                    }
                    break;
            }
            break;
        case 0xd00:
            break;
    }
}

- (void) playerBidding{
    if([[mightyGame player:self->biddingCount] isPato]){
        [NSTimer scheduledTimerWithTimeInterval:0.7 target:self selector:@selector(showPatoLabel) userInfo:nil repeats:NO];
    }
    else{
        declaration_t tmpDeclaration = [[[mightyGame player:self->biddingCount] AI] declaring_AI:pledge];
        
        if(tmpDeclaration.shp == -1){
            [NSTimer scheduledTimerWithTimeInterval:0.7 target:self selector:@selector(showPassLabel) userInfo:nil repeats:NO];
        }
        else{
            self->selectedGiruda = (NSInteger)tmpDeclaration.shp;
            self->selectedPledge = (NSInteger)tmpDeclaration.goal;
            [NSTimer scheduledTimerWithTimeInterval:0.7 target:self selector:@selector(showDeclarationLabel) userInfo:nil repeats:NO];
        }
    }
}

-(void) showDeclarationLabel{
    CGRect declarationLabelLocation;
    switch(self->biddingCount){
        case 0:
            declarationLabelLocation = CGRectMake(viewWidth*0.4f,viewHeight*0.7f, viewWidth*0.2f, viewHeight*0.08f);
            break;
        case 1:
            declarationLabelLocation = CGRectMake(viewWidth*0.1f,viewHeight*0.5f, viewWidth*0.2f, viewHeight*0.08f);
            break;
        case 2:
            declarationLabelLocation = CGRectMake(viewWidth*0.15f,viewHeight*0.1f, viewWidth*0.2f, viewHeight*0.08f);
            break;
        case 3:
            declarationLabelLocation = CGRectMake(viewWidth*0.65f,viewHeight*0.1f, viewWidth*0.2f, viewHeight*0.08f);
            break;
        case 4:
            declarationLabelLocation = CGRectMake(viewWidth*0.7f,viewHeight*0.5f, viewWidth*0.2f, viewHeight*0.08f);
            break;
        default:
            break;
    }
    giruda = self->selectedGiruda;
    pledge = self->selectedPledge;
    self->selectedGiruda = 0;
    self->selectedPledge = 0;
    self->declaringLabel = [[UILabel alloc] initWithFrame:declarationLabelLocation];
    switch(giruda){
        case SPADE:
            [self->declaringLabel setText:[NSString stringWithFormat:@"Spade %ld", pledge]];
            break;
        case DIAMOND:
            [self->declaringLabel setText:[NSString stringWithFormat:@"Diamond %ld", pledge]];
            break;
        case HEART:
            [self->declaringLabel setText:[NSString stringWithFormat:@"Heart %ld", pledge]];
            break;
        case CLOVER:
            [self->declaringLabel setText:[NSString stringWithFormat:@"Clover %ld", pledge]];
            break;
        case NOGIRUDA:
            [self->declaringLabel setText:[NSString stringWithFormat:@"No Giruda %ld", pledge]];
            break;
        default:
            break;
    }
    [self->declaringLabel setTextColor:[UIColor whiteColor]];
    self->declaringLabel.textAlignment = NSTextAlignmentCenter;
    self->declaringLabel.font = [UIFont systemFontOfSize:13.0f];
    [self->declaringLabel setBackgroundColor:[UIColor colorWithRed:0.05f green:0.254f blue:0.05f alpha:0.6f]];
    if(self->biddingCount == 0) [self->biddingView removeFromSuperview];
    [self.view addSubview:self->declaringLabel];
    self->isPassed[self->biddingCount] = 0;
    [NSTimer scheduledTimerWithTimeInterval:0.7 target:self selector:@selector(removeDeclaringView) userInfo:nil repeats:NO];
}

-(void) showPassLabel{
    CGRect passLabelLocation;
    switch(self->biddingCount){
        case 0:
            passLabelLocation = CGRectMake(viewWidth*0.4f,viewHeight*0.7f, viewWidth*0.2f, viewHeight*0.08f);
            break;
        case 1:
            passLabelLocation = CGRectMake(viewWidth*0.1f,viewHeight*0.5f, viewWidth*0.2f, viewHeight*0.08f);
            break;
        case 2:
            passLabelLocation = CGRectMake(viewWidth*0.15f,viewHeight*0.1f, viewWidth*0.2f, viewHeight*0.08f);
            break;
        case 3:
            passLabelLocation = CGRectMake(viewWidth*0.65f,viewHeight*0.1f, viewWidth*0.2f, viewHeight*0.08f);
            break;
        case 4:
            passLabelLocation = CGRectMake(viewWidth*0.7f,viewHeight*0.5f, viewWidth*0.2f, viewHeight*0.08f);
            break;
        default:
            break;
    }
    self->passLabel = [[UILabel alloc] initWithFrame:passLabelLocation];
    [self->passLabel setText:@"Pass!"];
    [self->passLabel setTextColor:[UIColor whiteColor]];
    self->passLabel.textAlignment = NSTextAlignmentCenter;
    self->passLabel.font = [UIFont systemFontOfSize:20.0f];
    [self->passLabel setBackgroundColor:[UIColor colorWithRed:0.05f green:0.254f blue:0.05f alpha:0.6f]];
    if(self->biddingCount == 0)    [self->biddingView removeFromSuperview];
    [self.view addSubview:self->passLabel];
    self->isPassed[self->biddingCount] = 1;
    [NSTimer scheduledTimerWithTimeInterval:0.7 target:self selector:@selector(removePassView) userInfo:nil repeats:NO];
}

-(void) showPatoLabel{
    NSInteger i=0;
    NSInteger cardIndex[10];
    CGRect patoCardLocation;
    if(self->biddingCount == 0)    [self->biddingView removeFromSuperview];
    if(self->biddingCount > 0){
        for(i=0;i<10;i++)   [cardBackView[(self->biddingCount-1)*10+i] removeFromSuperview];
        for(i=0;i<10;i++)   cardIndex[i] = [[mightyGame player:self->biddingCount] getCards:i];
    }
    for(i=0;i<10;i++){
        switch(self->biddingCount){
            case 1:
                patoCardLocation = CGRectMake(viewWidth*0.1f - cardWidth*0.5f,viewHeight*(0.3f+0.05f*(CGFloat)i),cardWidth,cardHeight);
                break;
            case 2:
                patoCardLocation = CGRectMake(viewWidth*0.125f+cardBackSpace*(CGFloat)i,viewHeight*0.04f,cardWidth,cardHeight);
                break;
            case 3:
                patoCardLocation = CGRectMake(viewWidth*0.5625f+cardBackSpace*(CGFloat)i,viewHeight*0.04f,cardWidth,cardHeight);
                break;
            case 4:
                patoCardLocation = CGRectMake(viewWidth*0.9f - cardWidth*0.5f,viewHeight*(0.3f+0.05f*(CGFloat)i),cardWidth,cardHeight);
                break;
        }
        if(self->biddingCount != 0){
            [self->playingCardView[cardIndex[i]] setFrame:patoCardLocation];
            [self.view addSubview:self->playingCardView[cardIndex[i]]];
        }
    }
    [NSTimer scheduledTimerWithTimeInterval:0.0f target:self selector:@selector(gameSet) userInfo:nil repeats:NO];
}

-(void) removeDeclaringView{
    NSInteger tmpBiddingCount = self->biddingCount;
    [self->declaringLabel removeFromSuperview];
    if(pledge == 20){
        [self->mightyGame setDeclarer:self->biddingCount];
        self->biddingCount = -1;
    }
    else if(self->isPassed[(tmpBiddingCount+1)%5] != 1){
        self->biddingCount = (tmpBiddingCount+1)%5;
    }
    else if(self->isPassed[(tmpBiddingCount+2)%5] != 1){
        self->biddingCount = (tmpBiddingCount+2)%5;
    }
    else if(self->isPassed[(tmpBiddingCount+3)%5] != 1){
        self->biddingCount = (tmpBiddingCount+3)%5;
    }
    else if(self->isPassed[(tmpBiddingCount+4)%5] != 1){
        self->biddingCount = (tmpBiddingCount+4)%5;
    }
    else{
        [self->mightyGame setDeclarer:self->biddingCount];
        self->biddingCount = -1;
    }
    switch(self->biddingCount){
        case 0:
            [NSTimer scheduledTimerWithTimeInterval:0.0f target:self selector:@selector(userBidding) userInfo:nil repeats:NO];
            break;
        case 1:
        case 2:
        case 3:
        case 4:
            [NSTimer scheduledTimerWithTimeInterval:0.0f target:self selector:@selector(playerBidding) userInfo:nil repeats:NO];
            break;
        default:
            [NSTimer scheduledTimerWithTimeInterval:0.0f target:self selector:@selector(showDeclarationResultLabel) userInfo:nil repeats:NO];
            break;
    }
}

-(void) removePassView{
    NSInteger tmpBiddingCount = self->biddingCount;
    [self->passLabel removeFromSuperview];
    if(isPassed[0] + isPassed[1] + isPassed[2] + isPassed[3] + isPassed[4] == 4){
        if(isPassed[0] == 0)        [self->mightyGame setDeclarer:0];
        else if(isPassed[1] == 0)   [self->mightyGame setDeclarer:1];
        else if(isPassed[2] == 0)   [self->mightyGame setDeclarer:2];
        else if(isPassed[3] == 0)   [self->mightyGame setDeclarer:3];
        else if(isPassed[4] == 0)   [self->mightyGame setDeclarer:4];
        else{
            NSLog(@"Error in function removePassView");
            exit(0);
        }
        [self showDeclarationResultLabel];
    }
    else{
        if(self->isPassed[(tmpBiddingCount+1)%5] != 1){
            self->biddingCount = (tmpBiddingCount+1)%5;
        }
        else if(self->isPassed[(tmpBiddingCount+2)%5] != 1){
            self->biddingCount = (tmpBiddingCount+2)%5;
        }
        else if(self->isPassed[(tmpBiddingCount+3)%5] != 1){
            self->biddingCount = (tmpBiddingCount+3)%5;
        }
        else if(self->isPassed[(tmpBiddingCount+4)%5] != 1){
            self->biddingCount = (tmpBiddingCount+4)%5;
        }
        else{
            self->biddingCount = 100;
        }
        switch(self->biddingCount){
            case 0:
                [self userBidding];
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                [self playerBidding];
                break;
            default:
                [self gameSet];
                break;
        }
    }
}

-(void) showDeclarationResultLabel{
    self->declarationResultLabel = [[UILabel alloc] initWithFrame:CGRectMake(viewWidth*0.07f, viewHeight*0.4f, viewWidth*0.8f, viewHeight*0.1f)];
    [mightyGame setGiruda:(NSInteger)giruda];
    mightyGame.goal = pledge;
    switch(giruda){
        case SPADE:
            [self->declarationResultLabel setText:[NSString stringWithFormat:@"Player %ld, Giruda: Spade, Pledge: %ld",[mightyGame declarer], pledge]];
            [self->girudaAtDeclarerInfo setImage:[UIImage imageNamed:@"spade.png"]];
            break;
        case DIAMOND:
            [self->declarationResultLabel setText:[NSString stringWithFormat:@"Player %ld, Giruda: Diamond, Pledge: %ld",[mightyGame declarer], pledge]];
            [self->girudaAtDeclarerInfo setImage:[UIImage imageNamed:@"diamond.png"]];
            break;
        case HEART:
            [self->declarationResultLabel setText:[NSString stringWithFormat:@"Player %ld, Giruda: Heart, Pledge: %ld",[mightyGame declarer], pledge]];
            [self->girudaAtDeclarerInfo setImage:[UIImage imageNamed:@"heart.png"]];
            break;
        case CLOVER:
            [self->declarationResultLabel setText:[NSString stringWithFormat:@"Player %ld, Giruda: Clover, Pledge: %ld",[mightyGame declarer], pledge]];
            [self->girudaAtDeclarerInfo setImage:[UIImage imageNamed:@"clover.png"]];
            break;
        case NOGIRUDA:
            [self->declarationResultLabel setText:[NSString stringWithFormat:@"Player %ld, Giruda: No Giruda, Pledge: %ld",[mightyGame declarer], pledge]];
            [self->girudaAtDeclarerInfo setImage:[UIImage imageNamed:@"nogiruda.png"]];
            break;
        default:
            NSLog(@"Error in showDeclararionResultLabel");
            exit(0);
            break;
    }
    [self->pledgeAtDeclarerInfo setText:[NSString stringWithFormat:@"%ld",pledge]];
    
    [self->declarationResultLabel setTextColor:[UIColor whiteColor]];
    self->declarationResultLabel.textAlignment = NSTextAlignmentCenter;
    self->declarationResultLabel.font = [UIFont systemFontOfSize:15.0f];
    [self->declarationResultLabel setBackgroundColor:[UIColor colorWithRed:0.05f green:0.254f blue:0.05f alpha:0.6f]];
    
    [self.view addSubview:self->declarationResultLabel];
    [self->nameTagView[[mightyGame declarer]].layer setBorderWidth:3.0f];
    [self->nameTagView[[mightyGame declarer]].layer setBorderColor:[[UIColor redColor] CGColor]];
    
    if([mightyGame declarer] == 0 || [mightyGame declarer] == 2 || [mightyGame declarer] == 3){
        [self->restThreeCardBack removeFromSuperview];
        [self->restThreeCardBack setImage:[UIImage imageNamed:@"b3fv.png"]];
        [self->restThreeCardBack setFrame:CGRectMake(viewWidth*0.5f - cardWidth*0.5f , viewHeight*0.5f - cardHeight*0.5f,cardWidth+cardBackSpace*2.0f, cardHeight)];
        [self.view addSubview:self->restThreeCardBack];
    }
    
    [UIView beginAnimations:nil context:NULL];
    [UIView setAnimationDuration:0.3];
    [UIView setAnimationDelegate:self];
    [UIView setAnimationRepeatAutoreverses:NO];
    [UIView setAnimationCurve:UIViewAnimationCurveLinear];
    switch([mightyGame declarer]){
        case 0:
            [self->restThreeCardBack setFrame:CGRectMake(viewWidth*0.5f - cardWidth*0.5f , viewHeight*0.8f,cardWidth+cardBackSpace*2.0f, cardHeight)];
            break;
        case 1:
            [self->restThreeCardBack setFrame:CGRectMake(viewWidth*0.1f - cardHeight*0.5f,viewHeight*0.5f - cardHeight*0.5f,cardHeight, cardWidth+cardBackSpace*2.0f)];
            break;
        case 2:
            [self->restThreeCardBack setFrame:CGRectMake(viewWidth*0.125f,viewHeight*0.04f,cardWidth+cardBackSpace*2.0f, cardHeight)];
            break;
        case 3:
            [self->restThreeCardBack setFrame:CGRectMake(viewWidth*0.5625f,viewHeight*0.04f,cardWidth+cardBackSpace*2.0f, cardHeight)];
            break;
        case 4:
            [self->restThreeCardBack setFrame:CGRectMake(viewWidth*0.9f - cardHeight*0.5f,viewHeight*0.5f - cardHeight*0.5f, cardHeight, cardWidth+cardBackSpace*2.0f)];
            break;
    }
    [UIView setAnimationDidStopSelector:@selector(removeRestThreeCards)];
    [UIView commitAnimations];
}

-(void) removeRestThreeCards{
    NSInteger i=0;
    [mightyGame giveThreeCards];
    [self->restThreeCardBack removeFromSuperview];
    switch([mightyGame declarer]){
        case 0:
            for(i=0;i<10;i++){
                [playingCardView[playerCard[i]] removeFromSuperview];
            }
            for(i=0;i<13;i++){
                playerCard[i] = [[mightyGame player:0] getCards:i];
            }
            for(i=0;i<13;i++){
                [playingCardView[playerCard[i]] setFrame:CGRectMake(mainPlayerCardAfterReceivingThreeCardsXPosition + cardColSpace * (CGFloat)i, userCardYPosition[0], cardWidth, cardHeight)];
                [self.view addSubview:playingCardView[playerCard[i]]];
            }
            break;
        case 1:
            for(i=0;i<3;i++){
                self->additionalThreeCards[i] = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"b1pb.png"]];
                [self->additionalThreeCards[i] setFrame:CGRectMake(userCardXPosition[1],userCardYPosition[1]+cardWidth+(float)(i+9)*cardBackSpace, cardHeight, cardBackSpace)];
            }
            break;
        case 2:
            for(i=0;i<3;i++){
                self->additionalThreeCards[i] = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"b1pr.png"]];
                [self->additionalThreeCards[i] setFrame:CGRectMake(userCardXPosition[2] + cardWidth + (float)(i+9) * cardBackSpace, userCardYPosition[2], cardBackSpace, cardHeight)];
            }
            break;
        case 3:
            for(i=0;i<3;i++){
                self->additionalThreeCards[i] = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"b1pr.png"]];
                [self->additionalThreeCards[i] setFrame:CGRectMake(userCardXPosition[3] + cardWidth + (float)(i+9) * cardBackSpace, userCardYPosition[3], cardBackSpace, cardHeight)];
            }
            break;
        case 4:
            for(i=0;i<3;i++){
                self->additionalThreeCards[i] = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"b1pb.png"]];
                [self->additionalThreeCards[i] setFrame:CGRectMake(userCardXPosition[4],userCardYPosition[4]+cardWidth+(float)(i+9)*cardBackSpace, cardHeight, cardBackSpace)];
            }
            break;
    }
    if([mightyGame declarer] != 0){
        [self.view addSubview:self->additionalThreeCards[0]];
        [self.view addSubview:self->additionalThreeCards[1]];
        [self.view addSubview:self->additionalThreeCards[2]];
    }
    [NSTimer scheduledTimerWithTimeInterval:0.7 target:self selector:@selector(showSelectingThreeCardsLabel) userInfo:nil repeats:NO];
}

-(void) showSelectingThreeCardsLabel{
    NSInteger i=0;
    CGFloat selectingThreeCardsViewWidth;
    CGFloat selectingThreeCardsViewHeight;
    self->selectingThreeCardsView = [[UIView alloc] init];
    [self->selectingThreeCardsView setBackgroundColor:[UIColor colorWithRed:0.05f green:0.254f blue:0.05f alpha:0.6f]];
    
    self->selectingThreeCardsLabel = [[UILabel alloc]init];
    [self->selectingThreeCardsLabel setFrame:CGRectMake(0.0f,viewHeight*0.025f,viewWidth*0.4f, viewHeight*0.08f)];
    [self->selectingThreeCardsLabel setTextColor:[UIColor whiteColor]];
    self->selectingThreeCardsLabel.textAlignment = NSTextAlignmentCenter;
    self->selectingThreeCardsLabel.font = [UIFont systemFontOfSize:15.0f];
    
    [self->declarationResultLabel removeFromSuperview];
    switch([mightyGame declarer]){
        case 0:
            selectingThreeCardsViewWidth = viewWidth*0.6f;
            selectingThreeCardsViewHeight = viewHeight*0.4f;
            [self->selectingThreeCardsView setFrame:CGRectMake(viewWidth*0.2f, viewHeight*0.3f, selectingThreeCardsViewWidth, selectingThreeCardsViewHeight)];
            self->discardingThreeCardsButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.86f, viewHeight*0.01f, viewWidth*0.08f, viewHeight*0.05f)];
            
            [self->selectingThreeCardsLabel setText:@"버릴 세 장의 카드를 선택하세요"];
            
            self->girudaButtons = [[NSArray alloc] initWithObjects:
                                   [[UIButton alloc] initWithFrame:CGRectMake(selectingThreeCardsViewWidth*0.1f,selectingThreeCardsViewHeight*0.2f,selectingThreeCardsViewWidth*0.38f,selectingThreeCardsViewHeight*0.12f)],
                                   [[UIButton alloc] initWithFrame:CGRectMake(selectingThreeCardsViewWidth*0.1f,selectingThreeCardsViewHeight*0.33f,selectingThreeCardsViewWidth*0.38f,selectingThreeCardsViewHeight*0.12f)],
                                   [[UIButton alloc] initWithFrame:CGRectMake(selectingThreeCardsViewWidth*0.1f,selectingThreeCardsViewHeight*0.46f,selectingThreeCardsViewWidth*0.38f,selectingThreeCardsViewHeight*0.12f)],
                                   [[UIButton alloc] initWithFrame:CGRectMake(selectingThreeCardsViewWidth*0.1f,selectingThreeCardsViewHeight*0.59f,selectingThreeCardsViewWidth*0.38f,selectingThreeCardsViewHeight*0.12f)],
                                   [[UIButton alloc] initWithFrame:CGRectMake(selectingThreeCardsViewWidth*0.1f,selectingThreeCardsViewHeight*0.72f,selectingThreeCardsViewWidth*0.38f,selectingThreeCardsViewHeight*0.12f)],
                                   nil];
            self->pledgeButtons = [[NSArray alloc] initWithObjects:
                                   [[UIButton alloc] initWithFrame:CGRectMake(selectingThreeCardsViewWidth*0.52f,selectingThreeCardsViewHeight*0.22f,selectingThreeCardsViewWidth*0.18f,selectingThreeCardsViewHeight*0.14f)],
                                   [[UIButton alloc] initWithFrame:CGRectMake(selectingThreeCardsViewWidth*0.52f,selectingThreeCardsViewHeight*0.37f,selectingThreeCardsViewWidth*0.18f,selectingThreeCardsViewHeight*0.14f)],
                                   [[UIButton alloc] initWithFrame:CGRectMake(selectingThreeCardsViewWidth*0.52f,selectingThreeCardsViewHeight*0.52f,selectingThreeCardsViewWidth*0.18f,selectingThreeCardsViewHeight*0.14f)],
                                   [[UIButton alloc] initWithFrame:CGRectMake(selectingThreeCardsViewWidth*0.52f,selectingThreeCardsViewHeight*0.67f,selectingThreeCardsViewWidth*0.18f,selectingThreeCardsViewHeight*0.14f)],
                                   [[UIButton alloc] initWithFrame:CGRectMake(selectingThreeCardsViewWidth*0.72f,selectingThreeCardsViewHeight*0.22f,selectingThreeCardsViewWidth*0.18f,selectingThreeCardsViewHeight*0.14f)],
                                   [[UIButton alloc] initWithFrame:CGRectMake(selectingThreeCardsViewWidth*0.72f,selectingThreeCardsViewHeight*0.37f,selectingThreeCardsViewWidth*0.18f,selectingThreeCardsViewHeight*0.14f)],
                                   [[UIButton alloc] initWithFrame:CGRectMake(selectingThreeCardsViewWidth*0.72f,selectingThreeCardsViewHeight*0.52f,selectingThreeCardsViewWidth*0.18f,selectingThreeCardsViewHeight*0.14f)],
                                   [[UIButton alloc] initWithFrame:CGRectMake(selectingThreeCardsViewWidth*0.72f,selectingThreeCardsViewHeight*0.67f,selectingThreeCardsViewWidth*0.18f,selectingThreeCardsViewHeight*0.14f)],
                                   nil];
            [(UIButton*)[self->girudaButtons objectAtIndex:0] setTitle:@"Spade" forState:UIControlStateNormal];
            [(UIButton*)[self->girudaButtons objectAtIndex:1] setTitle:@"Diamond" forState:UIControlStateNormal];
            [(UIButton*)[self->girudaButtons objectAtIndex:2] setTitle:@"Heart" forState:UIControlStateNormal];
            [(UIButton*)[self->girudaButtons objectAtIndex:3] setTitle:@"Clover" forState:UIControlStateNormal];
            [(UIButton*)[self->girudaButtons objectAtIndex:4] setTitle:@"No Giruda" forState:UIControlStateNormal];
            for(NSInteger i=0;i<5;i++){
                [(UIButton*)[self->girudaButtons objectAtIndex:i] setBackgroundColor:[UIColor colorWithRed:0.05f green:0.254f blue:0.05f alpha:1.0f]];
                [((UIButton*)[self->girudaButtons objectAtIndex:i]).layer setBorderWidth:3.0f];
                [((UIButton*)[self->girudaButtons objectAtIndex:i]).layer setBorderColor:[UIColor colorWithRed:0.5f green:0.5f blue:0.5f alpha:1.0f].CGColor];
                [(UIButton*)[self->girudaButtons objectAtIndex:i] setTitleColor:[UIColor colorWithRed:1.0f green:1.0f blue:0.3f alpha:1.0f] forState:UIControlStateNormal];
                [(UIButton*)[self->girudaButtons objectAtIndex:i] setEnabled:YES];
                [(UIButton*)[self->girudaButtons objectAtIndex:i] setTag:i+0xc00];
                [(UIButton*)[self->girudaButtons objectAtIndex:i] addTarget:self action:@selector(actionForChoosingGirudaAndPledge:) forControlEvents:UIControlEventTouchUpInside];
            }
            for(NSInteger i=0;i<8;i++){
                [(UIButton*)[self->pledgeButtons objectAtIndex:i] setTitle:[NSString stringWithFormat:@"%ld",i+13] forState:UIControlStateNormal];
                [(UIButton*)[self->pledgeButtons objectAtIndex:i] setBackgroundColor:[UIColor colorWithRed:0.05f green:0.254f blue:0.05f alpha:1.0f]];
                [((UIButton*)[self->pledgeButtons objectAtIndex:i]).layer setBorderWidth:3.0f];
                [((UIButton*)[self->pledgeButtons objectAtIndex:i]).layer setBorderColor:[UIColor colorWithRed:0.5f green:0.5f blue:0.5f alpha:1.0f].CGColor];
                [(UIButton*)[self->pledgeButtons objectAtIndex:i] setTag:i+0xd00];
                [(UIButton*)[self->pledgeButtons objectAtIndex:i] addTarget:self action:@selector(actionForChoosingGirudaAndPledge:) forControlEvents:UIControlEventTouchUpInside];
                if([mightyGame goal] <= (i+13)){
                    [(UIButton*)[self->pledgeButtons objectAtIndex:i] setTitleColor:[UIColor colorWithRed:1.0f green:1.0f blue:0.3f alpha:1.0f] forState:UIControlStateNormal];
                    [(UIButton*)[self->pledgeButtons objectAtIndex:i] setEnabled:YES];
                }
                else{
                    [(UIButton*)[self->pledgeButtons objectAtIndex:i] setTitleColor:[UIColor colorWithRed:0.5f green:0.5f blue:0.5f alpha:1.0f] forState:UIControlStateNormal];
                    [(UIButton*)[self->pledgeButtons objectAtIndex:i] setEnabled:NO];
                }
            }
            
            
            [self->discardingThreeCardsButton setTitle:@"버리기" forState:UIControlStateNormal];
            [self->discardingThreeCardsButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
            [self->discardingThreeCardsButton addTarget:self action:@selector(removeThreeCards) forControlEvents:UIControlEventTouchUpInside];
            self->discardingThreeCardsButton.enabled = NO;
            for(i=0;i<13;i++)   self->isSelected[i] = 0;
            for(i=0;i<13;i++){
                [self->playingCardView[playerCard[i]] setTag:(NSInteger)i];
            }
            for(NSInteger i=0;i<5;i++){
                [self->selectingThreeCardsView addSubview:[self->girudaButtons objectAtIndex:i]];
            }
            for(NSInteger i=0;i<8;i++){
                [self->selectingThreeCardsView addSubview:[self->pledgeButtons objectAtIndex:i]];
            }
            [self->selectingThreeCardsView addSubview:self->discardingThreeCardsButton];
            self->selectedGiruda = [mightyGame giruda];
            self->selectedPledge = [mightyGame goal];
            break;
        case 1:
            [self->selectingThreeCardsView setFrame:CGRectMake(viewWidth*0.2f, viewHeight*0.4f, viewWidth*0.4f, viewHeight*0.13f)];
            [self->selectingThreeCardsLabel setText:@"버릴 카드 세 장 선택 중.."];
            break;
        case 2:
            [self->selectingThreeCardsView setFrame:CGRectMake(viewWidth*0.2f, viewHeight*0.2f, viewWidth*0.4f, viewHeight*0.13f)];
            [self->selectingThreeCardsLabel setText:@"버릴 카드 세 장 선택 중.."];
            break;
        case 3:
            [self->selectingThreeCardsView setFrame:CGRectMake(viewWidth*0.5f, viewHeight*0.2f, viewWidth*0.4f, viewHeight*0.13f)];
            [self->selectingThreeCardsLabel setText:@"버릴 카드 세 장 선택 중.."];
            break;
        case 4:
            [self->selectingThreeCardsView setFrame:CGRectMake(viewWidth*0.5f, viewHeight*0.4f, viewWidth*0.4f, viewHeight*0.13f)];
            [self->selectingThreeCardsLabel setText:@"버릴 카드 세 장 선택 중.."];
            break;
    }
    [self->selectingThreeCardsView addSubview:self->selectingThreeCardsLabel];
    [self.view addSubview:self->selectingThreeCardsView];
    if([mightyGame declarer] != 0)  [NSTimer scheduledTimerWithTimeInterval:0.7 target:self selector:@selector(removeThreeCards) userInfo:nil repeats:NO];
}

-(void) removeThreeCards{
    NSInteger i=0;
    NSInteger c1=0, c2=0, c3=0;
    
    [self->selectingThreeCardsView removeFromSuperview];
    if([mightyGame declarer] == 0){
        for(c1=0;c1<13;c1++){
            if(isSelected[c1] == 1) break;
        }
        for(c2=c1+1;c2<13;c2++){
            if(isSelected[c2] == 1) break;
        }
        for(c3=c2+1;c3<13;c3++){
            if(isSelected[c3] == 1) break;
        }
        [self->playingCardView[playerCard[c1]] removeFromSuperview];
        [self->playingCardView[playerCard[c2]] removeFromSuperview];
        [self->playingCardView[playerCard[c3]] removeFromSuperview];
        [mightyGame removeThreeCards:c1 andSecond:c2 andThird:c3];
        for(i=0;i<10;i++){
            playerCard[i] = [[mightyGame player:0]getCards:i];
            [playingCardView[playerCard[i]] setFrame:CGRectMake(viewWidth*(0.15625f+(CGFloat)i * 0.0625f), userCardYPosition[0], cardWidth, cardHeight)];
            [playingCardView[playerCard[i]] setTag:1024];
        }
    }
    else{
        [self->additionalThreeCards[0] removeFromSuperview];
        [self->additionalThreeCards[1] removeFromSuperview];
        [self->additionalThreeCards[2] removeFromSuperview];
        [mightyGame chooseAndRemoveThreeCardsForComputer];
    }
    [mightyGame showPlayerscards];
    
    switch([mightyGame declarer]){
        case 0:
            [self->restThreeCardBack setImage:[UIImage imageNamed:@"b3fv.png"]];
            [self->restThreeCardBack setFrame:CGRectMake(viewWidth*0.5f - cardWidth*0.5f, userCardYPosition[0],cardWidth+cardBackSpace*2.0f, cardHeight)];
            break;
        case 1:
            [self->restThreeCardBack setImage:[UIImage imageNamed:@"b3fh.png"]];
            [self->restThreeCardBack setFrame:CGRectMake(viewWidth*0.1f - cardHeight*0.5f,viewHeight*0.5f - cardHeight*0.5f,cardHeight, cardWidth+cardBackSpace*2.0f)];
            break;
        case 2:
            [self->restThreeCardBack setImage:[UIImage imageNamed:@"b3fv.png"]];
            [self->restThreeCardBack setFrame:CGRectMake(viewWidth*0.125f,viewHeight*0.04f,cardWidth+cardBackSpace*2.0f, cardHeight)];
            break;
        case 3:
            [self->restThreeCardBack setImage:[UIImage imageNamed:@"b3fv.png"]];
            [self->restThreeCardBack setFrame:CGRectMake(viewWidth*0.5625f,viewHeight*0.04f,cardWidth+cardBackSpace*2.0f, cardHeight)];
            break;
        case 4:
            [self->restThreeCardBack setImage:[UIImage imageNamed:@"b3fh.png"]];
            [self->restThreeCardBack setFrame:CGRectMake(viewWidth*0.9f - cardHeight*0.5f,viewHeight*0.5f - cardHeight*0.5f, cardHeight, cardWidth+cardBackSpace*2.0f)];
            break;
    }
    [self.view addSubview:self->restThreeCardBack];
    
    [UIView beginAnimations:nil context:NULL];
    [UIView setAnimationDuration:0.3];
    [UIView setAnimationDelegate:self];
    [UIView setAnimationRepeatAutoreverses:NO];
    [UIView setAnimationCurve:UIViewAnimationCurveLinear];
    [UIView setAnimationDidStopSelector:@selector(showDeterminingFriendView)];
    
    if([mightyGame declarer] == 0 || [mightyGame declarer] == 2 || [mightyGame declarer] == 3){
        [self->restThreeCardBack setFrame:CGRectMake(viewWidth*0.5f - cardWidth*0.5f , viewHeight*0.5f - cardHeight*0.5f,cardWidth+cardBackSpace*2.0f, cardHeight)];
    }
    else{
        [self->restThreeCardBack setFrame:CGRectMake(viewWidth*0.5f - cardHeight*0.5f , viewHeight*0.5f - cardWidth*0.5f, cardHeight,cardWidth+cardBackSpace*2.0f)];
    }
    
    [UIView commitAnimations];
    
    
}

-(void) showDeterminingFriendView{
    NSInteger i=0;
    NSInteger j=0;
    [self->restThreeCardBack removeFromSuperview];
    if([mightyGame declarer] == 0){
        self->selectingFriendView = [[UIView alloc] initWithFrame:CGRectMake(viewWidth*0.1f, viewHeight*0.3f, viewWidth*0.8f, viewHeight*0.5f)];
        self->selectingFriendLabel = [[UILabel alloc] initWithFrame:CGRectMake(viewWidth*0.2f, viewHeight*0.02f, viewWidth*0.4f, viewHeight*0.05f)];
        self->mightyFriendButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.08f, viewHeight*0.1f, cardWidth, cardHeight)];
        self->jokerFriendButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.23f, viewHeight*0.1f, cardWidth, cardHeight)];
        if([mightyGame giruda] != NOGIRUDA){
            self->girudaAFriendButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.38f, viewHeight*0.1f, cardWidth, cardHeight)];
            switch([mightyGame giruda]){
                case SPADE:
                    [self->girudaAFriendButton setBackgroundImage:[UIImage imageNamed:@"s1.png"] forState:UIControlStateNormal];
                    [self->girudaAFriendButton setTag:0x100+51];
                    break;
                case DIAMOND:
                    [self->girudaAFriendButton setBackgroundImage:[UIImage imageNamed:@"d1.png"] forState:UIControlStateNormal];
                    [self->girudaAFriendButton setTag:0x100+38];
                    break;
                case HEART:
                    [self->girudaAFriendButton setBackgroundImage:[UIImage imageNamed:@"h1.png"] forState:UIControlStateNormal];
                    [self->girudaAFriendButton setTag:0x100+25];
                    break;
                case CLOVER:
                    [self->girudaAFriendButton setBackgroundImage:[UIImage imageNamed:@"c1.png"] forState:UIControlStateNormal];
                    [self->girudaAFriendButton setTag:0x100+12];
                    break;
            }
        }
        [self->girudaAFriendButton addTarget:self action:@selector(actionForChoosingFriend:) forControlEvents:UIControlEventTouchUpInside];
        [self->girudaAFriendButton.layer setBorderWidth:0.0f];
        [self->girudaAFriendButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
        
        self->playerOneFriend = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.6f,viewHeight*0.1f,viewWidth*0.15f, viewHeight*0.05f)];
        self->playerTwoFriend = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.6f,viewHeight*0.15f,viewWidth*0.15f, viewHeight*0.05f)];
        self->playerThreeFriend = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.6f,viewHeight*0.2f,viewWidth*0.15f, viewHeight*0.05f)];
        self->playerFourFriend = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.6f,viewHeight*0.25f,viewWidth*0.15f, viewHeight*0.05f)];
        self->chogooFriendButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.08f, viewHeight*0.22f, viewWidth*0.15f, viewHeight*0.05f)];
        self->noFriendButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.26f, viewHeight*0.22f, viewWidth*0.15f, viewHeight*0.05f)];
        self->friendConfirmButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.6f, viewHeight*0.02f, viewWidth*0.18f, viewHeight*0.04f)];
        
        for(i=0;i<4;i++){
            for(j=0;j<13;j++){
                self->playingCardPartialView[i*13+j] = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*(0.05f+0.7f*(CGFloat)j/13.0f),viewHeight*(0.3f+2.1f*(CGFloat)i/52.0),viewWidth*0.7f/13.0f, viewHeight*2.1f/52.0f)];
                [self->playingCardPartialView[i*13+j] setBackgroundImage:[UIImage imageWithCGImage:CGImageCreateWithImageInRect([[UIImage imageNamed:getFileName(i*13+j)] CGImage], CGRectMake(0.0f,0.0f,viewWidth*0.04f,viewHeight*0.03f))] forState:UIControlStateNormal];
                [self->playingCardPartialView[i*13+j] setTag:0x100+i*13+j];
                [self->playingCardPartialView[i*13+j] addTarget:self action:@selector(actionForChoosingFriend:) forControlEvents:UIControlEventTouchUpInside];
                [self->playingCardPartialView[i*13+j].layer setBorderWidth:0.0f];
                [self->playingCardPartialView[i*13+j].layer setBorderColor:[[UIColor blackColor] CGColor]];
                [self->selectingFriendView addSubview:self->playingCardPartialView[i*13+j]];
            }
        }
        [self->selectingFriendLabel setText:@"프렌드를 선택하세요"];
        
        if([mightyGame giruda] == SPADE){
            [self->mightyFriendButton setBackgroundImage:[UIImage imageNamed:@"d1.png"] forState:UIControlStateNormal];
            [self->mightyFriendButton setTag:0x100+38];
        }
        else{
            [self->mightyFriendButton setBackgroundImage:[UIImage imageNamed:@"s1.png"] forState:UIControlStateNormal];
            [self->mightyFriendButton setTag:0x100+51];
        }
        [self->mightyFriendButton addTarget:self action:@selector(actionForChoosingFriend:) forControlEvents:UIControlEventTouchUpInside];
        [self->mightyFriendButton.layer setBorderWidth:0.0f];
        [self->mightyFriendButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
        
        [self->jokerFriendButton setBackgroundImage:[UIImage imageNamed:@"joker.png"] forState:UIControlStateNormal];
        [self->jokerFriendButton addTarget:self action:@selector(actionForChoosingFriend:) forControlEvents:UIControlEventTouchUpInside];
        [self->jokerFriendButton setTag:0x100+52];
        [self->jokerFriendButton.layer setBorderWidth:0.0f];
        [self->jokerFriendButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
        
        [self->chogooFriendButton setTitle:@"초구 프렌드" forState:UIControlStateNormal];
        self->chogooFriendButton.enabled = YES;
        [self->chogooFriendButton addTarget:self action:@selector(actionForChoosingFriend:) forControlEvents:UIControlEventTouchUpInside];
        [self->chogooFriendButton setTag:0x300];
        [self->chogooFriendButton.layer setBorderWidth:0.0f];
        [self->chogooFriendButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
        
        [self->noFriendButton setTitle:@"노 프렌드" forState:UIControlStateNormal];
        self->noFriendButton.enabled = YES;
        [self->noFriendButton addTarget:self action:@selector(actionForChoosingFriend:) forControlEvents:UIControlEventTouchUpInside];
        [self->noFriendButton setTag:0x400];
        [self->noFriendButton.layer setBorderWidth:0.0f];
        [self->noFriendButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
        
        [self->playerOneFriend setTitle:[[mightyGame player:1] identifier] forState:UIControlStateNormal];
        self->playerOneFriend.enabled = YES;
        [self->playerOneFriend addTarget:self action:@selector(actionForChoosingFriend:) forControlEvents:UIControlEventTouchUpInside];
        [self->playerOneFriend setTag:0x201];
        [self->playerOneFriend.layer setBorderWidth:0.0f];
        [self->playerOneFriend.layer setBorderColor:[[UIColor blackColor] CGColor]];
        
        [self->playerTwoFriend setTitle:[[mightyGame player:2] identifier] forState:UIControlStateNormal];
        self->playerTwoFriend.enabled = YES;
        [self->playerTwoFriend addTarget:self action:@selector(actionForChoosingFriend:) forControlEvents:UIControlEventTouchUpInside];
        [self->playerTwoFriend setTag:0x202];
        [self->playerTwoFriend.layer setBorderWidth:0.0f];
        [self->playerTwoFriend.layer setBorderColor:[[UIColor blackColor] CGColor]];
        
        [self->playerThreeFriend setTitle:[[mightyGame player:3] identifier] forState:UIControlStateNormal];
        self->playerThreeFriend.enabled = YES;
        [self->playerThreeFriend addTarget:self action:@selector(actionForChoosingFriend:) forControlEvents:UIControlEventTouchUpInside];
        [self->playerThreeFriend setTag:0x203];
        [self->playerThreeFriend.layer setBorderWidth:0.0f];
        [self->playerThreeFriend.layer setBorderColor:[[UIColor blackColor] CGColor]];
        
        [self->playerFourFriend setTitle:[[mightyGame player:4] identifier] forState:UIControlStateNormal];
        self->playerFourFriend.enabled = YES;
        [self->playerFourFriend addTarget:self action:@selector(actionForChoosingFriend:) forControlEvents:UIControlEventTouchUpInside];
        [self->playerFourFriend setTag:0x204];
        [self->playerFourFriend.layer setBorderWidth:0.0f];
        [self->playerFourFriend.layer setBorderColor:[[UIColor blackColor] CGColor]];
        
        [self->friendConfirmButton setTitle:@"선택" forState:UIControlStateNormal];
        [self->friendConfirmButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        self->friendConfirmButton.enabled = NO;
        [self->friendConfirmButton addTarget:self action:@selector(actionForConfirmingFriend) forControlEvents:UIControlEventTouchUpInside];
        
        [self->selectingFriendView addSubview:self->mightyFriendButton];
        [self->selectingFriendView addSubview:self->jokerFriendButton];
        [self->selectingFriendView addSubview:self->girudaAFriendButton];
        [self->selectingFriendView addSubview:self->chogooFriendButton];
        [self->selectingFriendView addSubview:self->noFriendButton];
        [self->selectingFriendView addSubview:self->friendConfirmButton];
        [self->selectingFriendView addSubview:self->playerOneFriend];
        [self->selectingFriendView addSubview:self->playerTwoFriend];
        [self->selectingFriendView addSubview:self->playerThreeFriend];
        [self->selectingFriendView addSubview:self->playerFourFriend];
    }
    else{
        self->selectingFriendLabel = [[UILabel alloc] initWithFrame:CGRectMake(0.0f, viewHeight*0.03, viewWidth*0.4f, viewHeight*0.07f)];
        [self->selectingFriendLabel setText:@"프렌드 선택 중.."];
        switch([mightyGame declarer]){
            case 1:
                self->selectingFriendView = [[UIView alloc] initWithFrame:CGRectMake(viewWidth*0.2f, viewHeight*0.4f, viewWidth*0.4f, viewHeight*0.13f)];
                break;
            case 2:
                self->selectingFriendView = [[UIView alloc] initWithFrame:CGRectMake(viewWidth*0.2f, viewHeight*0.2f, viewWidth*0.4f, viewHeight*0.13f)];
                break;
            case 3:
                self->selectingFriendView = [[UIView alloc] initWithFrame:CGRectMake(viewWidth*0.5f, viewHeight*0.2f, viewWidth*0.4f, viewHeight*0.13f)];
                break;
            case 4:
                self->selectingFriendView = [[UIView alloc] initWithFrame:CGRectMake(viewWidth*0.5f, viewHeight*0.4f, viewWidth*0.4f, viewHeight*0.13f)];
                break;
        }
    }
    [self->selectingFriendLabel setTextColor:[UIColor whiteColor]];
    self->selectingFriendLabel.textAlignment = NSTextAlignmentCenter;
    self->selectingFriendLabel.font = [UIFont systemFontOfSize:20.0f];
    [self->selectingFriendView addSubview:self->selectingFriendLabel];
    [self->selectingFriendView setBackgroundColor:[UIColor colorWithRed:0.05f green:0.254f blue:0.05f alpha:0.6f]];
    [self.view addSubview:self->selectingFriendView];
    if([mightyGame declarer] != 0){
        [NSTimer scheduledTimerWithTimeInterval:0.7 target:self selector:@selector(showConfirmingFriendView) userInfo:nil repeats:NO];
    }
}

-(void) actionForChoosingFriend:(UIButton*) button{
    NSInteger i=0;
    [self->mightyFriendButton.layer setBorderWidth:0.0f];
    [self->mightyFriendButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
    [self->jokerFriendButton.layer setBorderWidth:0.0f];
    [self->jokerFriendButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
    [self->girudaAFriendButton.layer setBorderWidth:0.0f];
    [self->girudaAFriendButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
    [self->chogooFriendButton.layer setBorderWidth:0.0f];
    [self->chogooFriendButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
    [self->noFriendButton.layer setBorderWidth:0.0f];
    [self->noFriendButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
    [self->playerOneFriend.layer setBorderWidth:0.0f];
    [self->playerOneFriend.layer setBorderColor:[[UIColor blackColor] CGColor]];
    [self->playerTwoFriend.layer setBorderWidth:0.0f];
    [self->playerTwoFriend.layer setBorderColor:[[UIColor blackColor] CGColor]];
    [self->playerThreeFriend.layer setBorderWidth:0.0f];
    [self->playerThreeFriend.layer setBorderColor:[[UIColor blackColor] CGColor]];
    [self->playerFourFriend.layer setBorderWidth:0.0f];
    [self->playerFourFriend.layer setBorderColor:[[UIColor blackColor] CGColor]];
    for(i=0;i<52;i++){
        [self->playingCardPartialView[i].layer setBorderWidth:0.0f];
        [self->playingCardPartialView[i].layer setBorderColor:[[UIColor blackColor] CGColor]];
    }
    
    [button.layer setBorderWidth:3.0f];
    [button.layer setBorderColor:[[UIColor blueColor] CGColor]];
    [self->friendConfirmButton setTitleColor:[UIColor blueColor] forState:UIControlStateNormal];
    self->friendConfirmButton.enabled = YES;
    
    self->selectedFriend = [button tag];
}

-(void) actionForConfirmingFriend{
    NSInteger i=0;
    friendCard = -1;
    UIAlertView* warningForSelectingFriend;
    NSLog(@"선택");
    if(self->selectedFriend == 0x400){
        warningForSelectingFriend  = [[UIAlertView alloc] initWithTitle:@"" message:@"노 프렌드는 1:4로 플레이하므로 난이도가 높습니다. 그래도 계속하시겠습니까?" delegate:self cancelButtonTitle:@"Back" otherButtonTitles:@"Yes",nil];
        [warningForSelectingFriend setTag:0x123];
        [warningForSelectingFriend show];
    }
    else if((self->selectedFriend & 0xf00) == 0x200){
        warningForSelectingFriend  = [[UIAlertView alloc] initWithTitle:@"" message:@"프렌드를 지정하면 야당이 유리해집니다. 그래도 계속하시겠습니까?" delegate:self cancelButtonTitle:@"Back" otherButtonTitles:@"Yes",nil];
        [warningForSelectingFriend setTag:0x123];
        [warningForSelectingFriend show];
    }
    else if((self->selectedFriend & 0xf00) == 0x100){
        friendCard = self->selectedFriend - 0x100;
        for(i=0;i<10;i++){
            if(playerCard[i] == friendCard) break;
        }
        if(i != 10){
            warningForSelectingFriend  = [[UIAlertView alloc] initWithTitle:@"" message:@"가지고 있는 카드를 프렌드로 정하면 노프렌드가 됩니다. 그래도 계속하시겠습니까?" delegate:self cancelButtonTitle:@"Back" otherButtonTitles:@"Yes",nil];
            [warningForSelectingFriend setTag:0x123];
            [warningForSelectingFriend show];
        }
        else{
            [self showConfirmingFriendView];
        }
    }
    else{
        [self showConfirmingFriendView];
    }
}

-(void) showConfirmingFriendView{
    self->friendCard = 0;
    NSMutableString* declaredFriend;
    [self->selectingFriendView removeFromSuperview];
    self->confirmingFriendView = [[UIView alloc] initWithFrame:CGRectMake(viewWidth*0.3f, viewHeight*0.4f, viewWidth*0.4f, viewHeight*0.2f)];
    self->confirmingFriendLabel = [[UILabel alloc] initWithFrame:CGRectMake(viewWidth*0.05f, viewHeight*0.05f, viewWidth*0.3f, viewHeight*0.1f)];
    if([mightyGame declarer] == 0){
        friendCard = self->selectedFriend;
    }
    else{
        [mightyGame setChingooForAI];
        friendCard = (NSInteger)[mightyGame chingoo];
    }
    switch(friendCard & 0xf00){
        case 0x100:
            if((friendCard & 0xff) == 52)  declaredFriend = [[NSMutableString alloc] initWithString:@"조커"];
            else if(([mightyGame giruda] == SPADE) && ((friendCard & 0xff) == 38))  declaredFriend = [[NSMutableString alloc] initWithString:@"마이티"];
            else if(([mightyGame giruda] != SPADE) && ((friendCard & 0xff) == 51))  declaredFriend = [[NSMutableString alloc] initWithString:@"마이티"];
            else if((([mightyGame giruda] == SPADE) && ((friendCard&0xff) / 13 == 3)) || (([mightyGame giruda] == DIAMOND) && ((friendCard&0xff) / 13 == 2)) || (([mightyGame giruda] == HEART) && ((friendCard&0xff) / 13 == 1)) || (([mightyGame giruda] == CLOVER) && ((friendCard&0xff) / 13 == 0))){
                declaredFriend = [[NSMutableString alloc] initWithString:@"기루다 "];
                switch((friendCard & 0xff) % 13){
                    case 0:
                        [declaredFriend appendString:@"2"];
                        break;
                    case 1:
                        [declaredFriend appendString:@"3"];
                        break;
                    case 2:
                        [declaredFriend appendString:@"4"];
                        break;
                    case 3:
                        [declaredFriend appendString:@"5"];
                        break;
                    case 4:
                        [declaredFriend appendString:@"6"];
                        break;
                    case 5:
                        [declaredFriend appendString:@"7"];
                        break;
                    case 6:
                        [declaredFriend appendString:@"8"];
                        break;
                    case 7:
                        [declaredFriend appendString:@"9"];
                        break;
                    case 8:
                        [declaredFriend appendString:@"10"];
                        break;
                    case 9:
                        [declaredFriend appendString:@"J"];
                        break;
                    case 10:
                        [declaredFriend appendString:@"Q"];
                        break;
                    case 11:
                        [declaredFriend appendString:@"K"];
                        break;
                    case 12:
                        [declaredFriend appendString:@"A"];
                        break;
                }
            }
            else{
                switch((friendCard & 0xff) / 13){
                    case 0:
                        declaredFriend = [[NSMutableString alloc] initWithString:@"클로버 "];
                        break;
                    case 1:
                        declaredFriend = [[NSMutableString alloc] initWithString:@"하트 "];
                        break;
                    case 2:
                        declaredFriend = [[NSMutableString alloc] initWithString:@"다이아몬드 "];
                        break;
                    case 3:
                        declaredFriend = [[NSMutableString alloc] initWithString:@"스페이드 "];
                        break;
                }
                switch((friendCard & 0xff) % 13){
                    case 0:
                        [declaredFriend appendString:@"2"];
                        break;
                    case 1:
                        [declaredFriend appendString:@"3"];
                        break;
                    case 2:
                        [declaredFriend appendString:@"4"];
                        break;
                    case 3:
                        [declaredFriend appendString:@"5"];
                        break;
                    case 4:
                        [declaredFriend appendString:@"6"];
                        break;
                    case 5:
                        [declaredFriend appendString:@"7"];
                        break;
                    case 6:
                        [declaredFriend appendString:@"8"];
                        break;
                    case 7:
                        [declaredFriend appendString:@"9"];
                        break;
                    case 8:
                        [declaredFriend appendString:@"10"];
                        break;
                    case 9:
                        [declaredFriend appendString:@"J"];
                        break;
                    case 10:
                        [declaredFriend appendString:@"Q"];
                        break;
                    case 11:
                        [declaredFriend appendString:@"K"];
                        break;
                    case 12:
                        [declaredFriend appendString:@"A"];
                        break;
                }
            }
            break;
        case 0x200:
            declaredFriend = [[NSMutableString alloc] initWithFormat:@"%@",[[mightyGame player:friendCard&0xf] identifier]];
            [self->nameTagView[friendCard & 0xf].layer setBorderWidth:3.0f];
            [self->nameTagView[friendCard & 0xf].layer setBorderColor:[[UIColor orangeColor] CGColor]];
            break;
        case 0x300:
            declaredFriend = [[NSMutableString alloc] initWithString:@"초구"];
            break;
        case 0x400:
            declaredFriend = [[NSMutableString alloc] initWithString:@"노"];
            break;
    }
    [self->friendAtDeclarerInfo setText:declaredFriend];
    [self->confirmingFriendLabel setText:[NSString stringWithFormat:@"%@ 프렌드",declaredFriend]];
    [self->confirmingFriendLabel setTextColor:[UIColor whiteColor]];
    self->confirmingFriendLabel.textAlignment = NSTextAlignmentCenter;
    self->confirmingFriendLabel.font = [UIFont systemFontOfSize:15.0f];
    [self->confirmingFriendView setBackgroundColor:[UIColor colorWithRed:0.05f green:0.254f blue:0.05f alpha:0.6f]];
    [self->confirmingFriendView addSubview:self->confirmingFriendLabel];
    [self.view addSubview:self->confirmingFriendView];
    switch([mightyGame declarer]){
        case 0:
            [self->declarerInfoView setFrame:CGRectMake(viewWidth*0.5f+nameTagLabelWidth*0.5f,viewHeight*0.94f,declarerInfoViewWidth,declarerInfoViewHeight)];
            break;
        case 1:
            [self->declarerInfoView setFrame:CGRectMake(viewWidth*0.15f-declarerInfoViewWidth*0.5f,viewHeight*0.5f+nameTagLabelHeight,declarerInfoViewWidth,declarerInfoViewHeight)];
            break;
        case 2:
            [self->declarerInfoView setFrame:CGRectMake(viewWidth*9.0f/32.0f-declarerInfoViewWidth*0.5f,viewHeight*0.11f+nameTagLabelHeight,declarerInfoViewWidth,declarerInfoViewHeight)];
            break;
        case 3:
            [self->declarerInfoView setFrame:CGRectMake(viewWidth*23.0f/32.0f-declarerInfoViewWidth*0.5f,viewHeight*0.11f+nameTagLabelHeight,declarerInfoViewWidth,declarerInfoViewHeight)];
            break;
        case 4:
            [self->declarerInfoView setFrame:CGRectMake(viewWidth*0.85f-declarerInfoViewWidth*0.5f,viewHeight*0.5f+nameTagLabelHeight,declarerInfoViewWidth,declarerInfoViewHeight)];
            break;
    }
    [self.view addSubview:self->declarerInfoView];
    [NSTimer scheduledTimerWithTimeInterval:0.7 target:self selector:@selector(firstTurn) userInfo:nil repeats:NO];
}

-(void) firstTurn{
    self->starter = [mightyGame declarer];
    self->currentPlayer = self->starter;
    self->justPreviouslySelected = -1;
    self->submittingCount = 1;
    self->starterShape = -1;
    self->winner = -1;
    self->isJokerCall = NO;
    self->isJokerShown = NO;
    
    [self->confirmingFriendView removeFromSuperview];
    [mightyGame startFirstTurn];
    
    self->userSelectingCardView = [[UIView alloc] initWithFrame:CGRectMake(viewWidth*0.2f,viewHeight*0.5f,viewWidth*0.6f,viewHeight*0.15f)];
    [self->userSelectingCardView setBackgroundColor:[UIColor colorWithRed:0.05f green:0.254f blue:0.05f alpha:0.6f]];
    self->userSubmittingCardButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.2f, viewHeight*0.02f, viewWidth*0.2f, viewHeight*0.04f)];
    [self->userSubmittingCardButton setTitle:@"내기" forState:UIControlStateNormal];
    [self->userSubmittingCardButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [self->userSubmittingCardButton addTarget:self action:@selector(actionForSubmittingCard) forControlEvents:UIControlEventTouchUpInside];
    self->userSubmittingCardButton.enabled = NO;
    
    self->jokerSpadeCallButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.03f, viewHeight*0.08f, viewWidth*0.1f, viewHeight*0.05f)];
    self->jokerDiamondCallButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.14f, viewHeight*0.08f, viewWidth*0.1f, viewHeight*0.05f)];
    self->jokerHeartCallButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.25f, viewHeight*0.08f, viewWidth*0.1f, viewHeight*0.05f)];
    self->jokerCloverCallButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.36f, viewHeight*0.08f, viewWidth*0.1f, viewHeight*0.05f)];
    self->jokerAnyCallButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.47f, viewHeight*0.08f, viewWidth*0.1f, viewHeight*0.05f)];
    self->jokercallOKButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.06f, viewHeight*0.08f, viewWidth*0.21f, viewHeight*0.05f)];
    self->jokercallCancelButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.36f, viewHeight*0.08f, viewWidth*0.21f, viewHeight*0.05f)];
    
    [self->jokerSpadeCallButton setTitle:@"Spade" forState:UIControlStateNormal];
    [self->jokerDiamondCallButton setTitle:@"Diamond" forState:UIControlStateNormal];
    [self->jokerHeartCallButton setTitle:@"Heart" forState:UIControlStateNormal];
    [self->jokerCloverCallButton setTitle:@"Clover" forState:UIControlStateNormal];
    [self->jokerAnyCallButton setTitle:@"Any" forState:UIControlStateNormal];
    [self->jokercallOKButton setTitle:@"Joker Call" forState:UIControlStateNormal];
    [self->jokercallCancelButton setTitle:@"No joker call" forState:UIControlStateNormal];
    
    [self->jokerSpadeCallButton setTitleColor:[UIColor orangeColor] forState:UIControlStateNormal];
    [self->jokerDiamondCallButton setTitleColor:[UIColor orangeColor] forState:UIControlStateNormal];
    [self->jokerHeartCallButton setTitleColor:[UIColor orangeColor] forState:UIControlStateNormal];
    [self->jokerCloverCallButton setTitleColor:[UIColor orangeColor] forState:UIControlStateNormal];
    [self->jokerAnyCallButton setTitleColor:[UIColor orangeColor] forState:UIControlStateNormal];
    [self->jokercallOKButton setTitleColor:[UIColor yellowColor] forState:UIControlStateNormal];
    [self->jokercallCancelButton setTitleColor:[UIColor yellowColor] forState:UIControlStateNormal];
    
    [self->jokerSpadeCallButton setTag:0x601];
    [self->jokerDiamondCallButton setTag:0x602];
    [self->jokerHeartCallButton setTag:0x603];
    [self->jokerCloverCallButton setTag:0x604];
    [self->jokerAnyCallButton setTag:0x605];
    [self->jokercallOKButton setTag:0x606];
    [self->jokercallCancelButton setTag:0x607];
    
    [self->jokerSpadeCallButton addTarget:self action:@selector(actionForChoosingJokerButton:) forControlEvents:UIControlEventTouchUpInside];
    [self->jokerDiamondCallButton addTarget:self action:@selector(actionForChoosingJokerButton:) forControlEvents:UIControlEventTouchUpInside];
    [self->jokerHeartCallButton addTarget:self action:@selector(actionForChoosingJokerButton:) forControlEvents:UIControlEventTouchUpInside];
    [self->jokerCloverCallButton addTarget:self action:@selector(actionForChoosingJokerButton:) forControlEvents:UIControlEventTouchUpInside];
    [self->jokerAnyCallButton addTarget:self action:@selector(actionForChoosingJokerButton:) forControlEvents:UIControlEventTouchUpInside];
    [self->jokercallOKButton addTarget:self action:@selector(actionForChoosingJokercallButton:) forControlEvents:UIControlEventTouchUpInside];
    [self->jokercallCancelButton addTarget:self action:@selector(actionForChoosingJokercallButton:) forControlEvents:UIControlEventTouchUpInside];
    
    self->jokerSpadeCallButton.enabled = YES;
    self->jokerDiamondCallButton.enabled = YES;
    self->jokerHeartCallButton.enabled = YES;
    self->jokerCloverCallButton.enabled = YES;
    self->jokerAnyCallButton.enabled = YES;
    self->jokercallOKButton.enabled = YES;
    self->jokercallCancelButton.enabled = YES;
    
    [self->userSelectingCardView addSubview:self->userSubmittingCardButton];
    
    [self submittingCard];
}

-(void) Turns{
    self->starter = self->winner;
    self->currentPlayer = self->starter;
    self->justPreviouslySelected = -1;
    self->submittingCount++;
    self->winner = -1;
    self->starterShape = -1;
    self->isJokerCall = NO;
    self->userSubmittingCardButton.enabled = NO;
    [self->userSubmittingCardButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [mightyGame nextTurn:self->starter];
    [self submittingCard];
}

-(void) gameSet{
    NSInteger i=0;
    NSInteger numOfDangsoo = 0;
    NSInteger maxObtainedCards = 0;
    NSInteger otherPartyScore = 0;
    NSString* messageTitle;
    NSString* messageContext;
    UIAlertView* gameResultMessage;
    if(self->submittingCount == 10){
        for(i=0, otherPartyScore = 0; i<5;i++){
            if((i == [mightyGame declarer]) || (i == [mightyGame knownChingoo])){
                continue;
            }
            else    otherPartyScore += [[mightyGame player:i] obtainedScore];
        }
        if(otherPartyScore <= 20 - [mightyGame goal]){
            messageContext = [NSString stringWithFormat:@"여당 승!\n여당 득점: %ld, 야당 득점: %ld",20-otherPartyScore,otherPartyScore];
            if(([mightyGame declarer] == 0) || ([mightyGame knownChingoo] == 0)){
                messageTitle = @"승리!";
            }
            else{
                messageTitle = @"패배..";
            }
            self->dealer = [mightyGame knownChingoo];
        }
        else{
            messageContext = [NSString stringWithFormat:@"야당 승!\n여당 득점: %ld, 야당 득점: %ld",20-otherPartyScore,otherPartyScore];
            if(([mightyGame declarer] == 0) || ([mightyGame knownChingoo] == 0)){
                messageTitle = @"패배..";
            }
            else{
                messageTitle = @"승리";
            }
            for(i=0, numOfDangsoo = 0, maxObtainedCards = 0; i<5;i++){
                if((i != [mightyGame declarer]) && (i != [mightyGame knownChingoo])){
                    if([[mightyGame player:i] obtainedScore] > maxObtainedCards)    maxObtainedCards = [[mightyGame player:i] obtainedScore];
                }
            }
            for(i=0, numOfDangsoo = 0, maxObtainedCards = 0; i<5;i++){
                if((i != [mightyGame declarer]) && (i != [mightyGame knownChingoo])){
                    if([[mightyGame player:i] obtainedScore] == maxObtainedCards)    maxObtainedCards = [[mightyGame player:i] obtainedScore];
                }
            }
        }
        NSLog(@"야당 득점 = %ld, 목표점수: %ld",otherPartyScore, [mightyGame goal]);
    }
    else{
        messageTitle = @"파토!";
        messageContext = @"";
    }
    gameResultMessage = [[UIAlertView alloc] initWithTitle:messageTitle message:messageContext delegate:self cancelButtonTitle:@"끝내기" otherButtonTitles:@"새 게임", nil];
    [gameResultMessage setTag:0x234];
    [gameResultMessage show];
    //    [self.view addSubview:self->resultView];
}

-(void) submittingCard{
    NSInteger i=0;
    NSInteger locationOfJoker = 0;
    NSInteger numOfSameShapeCards = 0;
    NSInteger numOfHoldingCards = 0;
    BOOL isThereJoker = NO;
    NSMutableString* Log = [[NSMutableString alloc] initWithString:@"Tags:\n"];
    
    if(self->currentPlayer == 0){
        numOfHoldingCards = 11 - self->submittingCount;
        numOfSameShapeCards = 0;
        isThereJoker = NO;
        if(self->starter == 0){
            if((self->submittingCount == 1) && ([mightyGame giruda] != NOGIRUDA)){
                switch([mightyGame giruda]){
                    case SPADE:
                        for(i=0;i<numOfHoldingCards;i++){
                            if(self->playerCard[i] / 13 == 3){
                                numOfSameShapeCards++;
                                [self->playingCardView[self->playerCard[i]] setTag:(0x20|i)];
                            }
                            else{
                                [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                                if(self->playerCard[i] == 52)  isThereJoker = YES;
                            }
                        }
                        break;
                    case DIAMOND:
                        for(i=0;i<numOfHoldingCards;i++){
                            if(self->playerCard[i] / 13 == 2){
                                numOfSameShapeCards++;
                                [self->playingCardView[self->playerCard[i]] setTag:(0x20|i)];
                            }
                            else{
                                [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                                if(self->playerCard[i] == 52)  isThereJoker = YES;
                            }
                        }
                        break;
                    case HEART:
                        for(i=0;i<numOfHoldingCards;i++){
                            if(self->playerCard[i] / 13 == 1){
                                numOfSameShapeCards++;
                                [self->playingCardView[self->playerCard[i]] setTag:(0x20|i)];
                            }
                            else{
                                [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                                if(self->playerCard[i] == 52)  isThereJoker = YES;
                            }
                        }
                        break;
                    case CLOVER:
                        for(i=0;i<numOfHoldingCards;i++){
                            if(self->playerCard[i] / 13 == 0){
                                numOfSameShapeCards++;
                                [self->playingCardView[self->playerCard[i]] setTag:(0x20|i)];
                            }
                            else{
                                [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                                if(self->playerCard[i] == 52)  isThereJoker = YES;
                            }
                        }
                        break;
                }
                if((numOfSameShapeCards == 10) || ((numOfSameShapeCards == 9) & isThereJoker)){
                    for(i=0;i<numOfHoldingCards;i++){
                        [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                    }
                }
            }
            else{
                for(i=0;i<numOfHoldingCards;i++){
                    [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                }
            }
        }
        else{
            for(locationOfJoker=0;locationOfJoker<numOfHoldingCards;locationOfJoker++){
                if(self->playerCard[locationOfJoker] == 52)   break;
            }
            if((locationOfJoker<numOfHoldingCards) && (self->isJokerCall)){
                for(i=0;i<numOfHoldingCards;i++){
                    [self->playingCardView[self->playerCard[i]] setTag:(0x20|i)];
                }
                [self->playingCardView[self->playerCard[locationOfJoker]] setTag:(0x10|i)];
            }
            else{
                switch(self->starterShape){
                    case SPADE:
                        for(i=0, numOfSameShapeCards = 0;i<numOfHoldingCards;i++){
                            if(self->playerCard[i] / 13 == 3){
                                numOfSameShapeCards ++;
                                [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                            }
                            else{
                                [self->playingCardView[self->playerCard[i]] setTag:(0x20|i)];
                            }
                        }
                        if(numOfSameShapeCards == 0){
                            for(i=0;i<numOfHoldingCards;i++){
                                [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                            }
                        }
                        else{
                            for(i=0;i<numOfHoldingCards;i++){
                                if((self->playerCard[i] == 52) || (([mightyGame giruda] == SPADE) && (self->playerCard[i] == 38))){
                                    [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                                }
                            }
                        }
                        break;
                    case DIAMOND:
                        for(i=0, numOfSameShapeCards = 0;i<numOfHoldingCards;i++){
                            if(self->playerCard[i] / 13 == 2){
                                numOfSameShapeCards ++;
                                [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                            }
                            else{
                                [self->playingCardView[self->playerCard[i]] setTag:(0x20|i)];
                            }
                        }
                        if(numOfSameShapeCards == 0){
                            for(i=0;i<numOfHoldingCards;i++){
                                [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                            }
                        }
                        else{
                            for(i=0;i<numOfHoldingCards;i++){
                                if((self->playerCard[i] == 52) || (([mightyGame giruda] != SPADE) && (self->playerCard[i] == 51))){
                                    [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                                }
                            }
                        }
                        break;
                    case HEART:
                        for(i=0, numOfSameShapeCards = 0;i<numOfHoldingCards;i++){
                            if(self->playerCard[i] / 13 == 1){
                                numOfSameShapeCards ++;
                                [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                            }
                            else{
                                [self->playingCardView[self->playerCard[i]] setTag:(0x20|i)];
                            }
                        }
                        if(numOfSameShapeCards == 0){
                            for(i=0;i<numOfHoldingCards;i++){
                                [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                            }
                        }
                        else{
                            for(i=0;i<numOfHoldingCards;i++){
                                if((self->playerCard[i] == 52) || (([mightyGame giruda] != SPADE) && (self->playerCard[i] == 51)) || (([mightyGame giruda] == SPADE) && (self->playerCard[i] == 38))){
                                    [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                                }
                            }
                        }
                        break;
                    case CLOVER:
                        for(i=0, numOfSameShapeCards = 0;i<numOfHoldingCards;i++){
                            if(self->playerCard[i] / 13 == 0){
                                numOfSameShapeCards ++;
                                [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                            }
                            else{
                                [self->playingCardView[self->playerCard[i]] setTag:(0x20|i)];
                            }
                        }
                        if(numOfSameShapeCards == 0){
                            for(i=0;i<numOfHoldingCards;i++){
                                [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                            }
                        }
                        else{
                            for(i=0;i<numOfHoldingCards;i++){
                                if((self->playerCard[i] == 52) || (([mightyGame giruda] != SPADE) && (self->playerCard[i] == 51)) || (([mightyGame giruda] == SPADE) && (self->playerCard[i] == 38))){
                                    [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                                }
                            }
                        }
                        break;
                    case ANYCARD:
                        for(i=0;i<numOfHoldingCards;i++){
                            [self->playingCardView[self->playerCard[i]] setTag:(0x10|i)];
                        }
                        break;
                }
            }
            for(i=0;i<numOfHoldingCards;i++){
                [Log appendFormat:@"0x%lx, ",[self->playingCardView[self->playerCard[i]] tag]];
            }
            NSLog(@"%@",Log);
        }
        [self.view addSubview:userSelectingCardView];
    }
    else{
        [NSTimer scheduledTimerWithTimeInterval:0.7 target:self selector:@selector(actionForSubmittingCard) userInfo:nil repeats:NO];
    }
    return;
}

-(void) actionForChoosingCard:(UIButton*) button{
    NSInteger i=0;
    NSInteger sum=0;
    
    CGRect destinationOfCard;
    CGRect destinationOfPreviousCard;
    if(([button tag] & 0xf0) == 0){
        if(isSelected[[button tag]] == 0){
            isSelected[[button tag]] = 1;
            destinationOfCard = CGRectMake(viewWidth*(0.09375f+0.0625f*(CGFloat)[button tag]), userCardYPosition[0]-gapOfYPositionOfChosenCard, cardWidth, cardHeight);
        }
        else{
            isSelected[[button tag]] = 0;
            destinationOfCard = CGRectMake(viewWidth*(0.09375f+0.0625f*(CGFloat)[button tag]), userCardYPosition[0], cardWidth, cardHeight);
        }
        [UIView beginAnimations:nil context:NULL];
        [UIView setAnimationDuration:0.08f];
        [UIView setAnimationDelegate:self];
        [UIView setAnimationRepeatAutoreverses:NO];
        [UIView setAnimationCurve:UIViewAnimationCurveLinear];
        [self->playingCardView[playerCard[[button tag]]] setFrame:destinationOfCard];
        [UIView commitAnimations];
        
        for(i=0;i<13;i++)   sum += isSelected[i];
        if(sum == 3){
            [self->discardingThreeCardsButton setTitleColor:[UIColor redColor] forState:UIControlStateNormal];
            self->discardingThreeCardsButton.enabled = YES;
        }
        else{
            [self->discardingThreeCardsButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
            self->discardingThreeCardsButton.enabled = NO;
        }
    }
    else if(([button tag] & 0xf0) == 0x10){
        if(self->justPreviouslySelected != ([button tag] & 0xf)){
            destinationOfCard = [self->playingCardView[playerCard[[button tag] & 0xf]] frame];
            destinationOfCard.origin.y -= gapOfYPositionOfChosenCard;
            [UIView beginAnimations:nil context:NULL];
            [UIView setAnimationDuration:0.08f];
            [UIView setAnimationDelegate:self];
            [UIView setAnimationRepeatAutoreverses:NO];
            [UIView setAnimationCurve:UIViewAnimationCurveLinear];
            [self->playingCardView[playerCard[[button tag] & 0xf]] setFrame:destinationOfCard];
            if(self->justPreviouslySelected != -1){
                destinationOfPreviousCard = [self->playingCardView[playerCard[self->justPreviouslySelected]] frame];
                destinationOfPreviousCard.origin.y += gapOfYPositionOfChosenCard;
                [self->playingCardView[playerCard[self->justPreviouslySelected]] setFrame:destinationOfPreviousCard];
            }
            [UIView commitAnimations];
            if((playerCard[[button tag] & 0xf] == 52) && (self->starter == 0)){
                if((((playerCard[self->justPreviouslySelected] == 1) && ([mightyGame giruda] != CLOVER)) || ((playerCard[self->justPreviouslySelected] == 40) && ([mightyGame giruda] == CLOVER))) &&
                   (self->submittingCount > 1) && (self->submittingCount < 10) &&
                   (self->starter == 0) && (self->isJokerShown == NO)){
                    [self->jokercallOKButton removeFromSuperview];
                    [self->jokercallCancelButton removeFromSuperview];
                }
                [self->userSelectingCardView addSubview:self->jokerSpadeCallButton];
                [self->userSelectingCardView addSubview:self->jokerDiamondCallButton];
                [self->userSelectingCardView addSubview:self->jokerHeartCallButton];
                [self->userSelectingCardView addSubview:self->jokerCloverCallButton];
                [self->userSelectingCardView addSubview:self->jokerAnyCallButton];
                self->userSubmittingCardButton.enabled = NO;
                [self->userSubmittingCardButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
            }
            else if((((playerCard[[button tag] & 0xf] == 1) && ([mightyGame giruda] != CLOVER)) ||
                     ((playerCard[[button tag] & 0xf] == 40) && ([mightyGame giruda] == CLOVER))) &&
                    (self->submittingCount > 1) && (self->submittingCount < 10) &&
                    (self->starter == 0) && (self->isJokerShown == NO)){
                if((playerCard[self->justPreviouslySelected] == 52) && (self->starter == 0)){
                    [self->jokerSpadeCallButton removeFromSuperview];
                    [self->jokerDiamondCallButton removeFromSuperview];
                    [self->jokerHeartCallButton removeFromSuperview];
                    [self->jokerCloverCallButton removeFromSuperview];
                    [self->jokerAnyCallButton removeFromSuperview];
                }
                [self->userSelectingCardView addSubview:self->jokercallOKButton];
                [self->userSelectingCardView addSubview:self->jokercallCancelButton];
                self->userSubmittingCardButton.enabled = NO;
                [self->userSubmittingCardButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
            }
            else{
                NSLog(@"%ld",self->justPreviouslySelected);
                if((playerCard[self->justPreviouslySelected] == 52) && (self->starter == 0)){
                    [self->jokerSpadeCallButton removeFromSuperview];
                    [self->jokerDiamondCallButton removeFromSuperview];
                    [self->jokerHeartCallButton removeFromSuperview];
                    [self->jokerCloverCallButton removeFromSuperview];
                    [self->jokerAnyCallButton removeFromSuperview];
                }
                else if((((playerCard[self->justPreviouslySelected] == 1) && ([mightyGame giruda] != CLOVER)) || ((playerCard[self->justPreviouslySelected] == 40) && ([mightyGame giruda] == CLOVER))) &&
                        (self->submittingCount > 1) && (self->submittingCount < 10) &&
                        (self->starter == 0) && (self->isJokerShown == NO)){
                    [self->jokercallOKButton removeFromSuperview];
                    [self->jokercallCancelButton removeFromSuperview];
                }
                self->userSubmittingCardButton.enabled = YES;
                [self->userSubmittingCardButton setTitleColor:[UIColor redColor] forState:UIControlStateNormal];
            }
        }
        self->justPreviouslySelected = [button tag] & 0xf;
    }
}


-(void) alertView:(UIAlertView*)alertView clickedButtonAtIndex:(NSInteger) buttonIndex{
    if([alertView tag] == 0x123){
        if(buttonIndex == 1){
            [self showConfirmingFriendView];
        }
    }
    else if([alertView tag] == 0x234){
        if(buttonIndex == 0) {
            [self.view removeFromSuperview];
        }
        else{
            [[self.view subviews] makeObjectsPerformSelector:@selector(removeFromSuperview)];
            [NSTimer scheduledTimerWithTimeInterval:0.0f target:self selector:@selector(start) userInfo:nil repeats:NO];
        }
    }
}

-(void) actionForChoosingJokerButton:(UIButton*) button{
    [self->jokerSpadeCallButton.layer setBorderWidth:0.0f];
    [self->jokerSpadeCallButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
    [self->jokerDiamondCallButton.layer setBorderWidth:0.0f];
    [self->jokerDiamondCallButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
    [self->jokerHeartCallButton.layer setBorderWidth:0.0f];
    [self->jokerHeartCallButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
    [self->jokerCloverCallButton.layer setBorderWidth:0.0f];
    [self->jokerCloverCallButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
    [self->jokerAnyCallButton.layer setBorderWidth:0.0f];
    [self->jokerAnyCallButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
    
    switch([button tag]){
        case 0x601:
            self->jokerShape = SPADE;
            break;
        case 0x602:
            self->jokerShape = DIAMOND;
            break;
        case 0x603:
            self->jokerShape = HEART;
            break;
        case 0x604:
            self->jokerShape = CLOVER;
            break;
        case 0x605:
            self->jokerShape = ANYCARD;
            break;
    }
    [button.layer setBorderWidth:3.0f];
    [button.layer setBorderColor:[[UIColor blueColor] CGColor]];
    self->userSubmittingCardButton.enabled = YES;
    [self->userSubmittingCardButton setTitleColor:[UIColor redColor] forState:UIControlStateNormal];
}

-(void) actionForChoosingJokercallButton:(UIButton*) button{
    [self->jokercallOKButton.layer setBorderWidth:0.0f];
    [self->jokercallOKButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
    [self->jokercallCancelButton.layer setBorderWidth:0.0f];
    [self->jokercallCancelButton.layer setBorderColor:[[UIColor blackColor] CGColor]];
    
    switch([button tag]){
        case 0x606:
            self->isJokerCall = YES;
            break;
        case 0x607:
            self->isJokerCall = NO;
            break;
    }
    
    [button.layer setBorderWidth:3.0f];
    [button.layer setBorderColor:[[UIColor blueColor] CGColor]];
    self->userSubmittingCardButton.enabled = YES;
    [self->userSubmittingCardButton setTitleColor:[UIColor redColor] forState:UIControlStateNormal];
    
}

-(void) actionForSubmittingCard{
    NSInteger i=0;
    NSInteger submitCard;
    NSInteger numOfRemainedCards = 10 - self->submittingCount;
    NSLog(@"%ld",self->submittingCount);
    if(self->currentPlayer == 0){
        self->submitCardNumber[0] = submitCard = self->playerCard[self->justPreviouslySelected];
        [[mightyGame player:0] removeCard:self->justPreviouslySelected];
        [mightyGame submitCard:submitCard];
        if(self->starter == 0){
            switch(self->submitCardNumber[0] / 13){
                case 0:
                    self->starterShape = CLOVER;
                    if((self->submitCardNumber[0] == 1) && ([mightyGame giruda] != CLOVER)){
                        [self->jokercallOKButton removeFromSuperview];
                        [self->jokercallCancelButton removeFromSuperview];
                    }
                    break;
                case 1:
                    self->starterShape = HEART;
                    break;
                case 2:
                    self->starterShape = DIAMOND;
                    break;
                case 3:
                    self->starterShape = SPADE;
                    if((self->submitCardNumber[0] == 40) && ([mightyGame giruda] == CLOVER)){
                        [self->jokercallOKButton removeFromSuperview];
                        [self->jokercallCancelButton removeFromSuperview];
                    }
                    break;
                case 4:
                    self->starterShape = self->jokerShape;
                    [self->jokerSpadeCallButton removeFromSuperview];
                    [self->jokerDiamondCallButton removeFromSuperview];
                    [self->jokerHeartCallButton removeFromSuperview];
                    [self->jokerCloverCallButton removeFromSuperview];
                    [self->jokerAnyCallButton removeFromSuperview];
                    switch(self->jokerShape){
                        case SPADE:
                            [self->jokerShapeLabel setText:@"Spade"];
                            break;
                        case DIAMOND:
                            [self->jokerShapeLabel setText:@"Diamond"];
                            break;
                        case HEART:
                            [self->jokerShapeLabel setText:@"Heart"];
                            break;
                        case CLOVER:
                            [self->jokerShapeLabel setText:@"Clover"];
                            break;
                        case ANYCARD:
                            [self->jokerShapeLabel setText:@"Any Card"];
                            break;
                    }
                    [self->jokerShapeView setFrame:CGRectMake(viewWidth*0.35f, viewHeight*0.7f, jokerShapeViewWidth, jokerShapeViewHeight)];
                    [self.view addSubview:self->jokerShapeView];
                    break;
            }
        }
        if(self->isJokerCall){
            [self->jokerShapeLabel setText:@"Joker Call!"];
            [self->jokerShapeView setFrame:CGRectMake(viewWidth*0.35f, viewHeight*0.7f, jokerShapeViewWidth, jokerShapeViewHeight)];
            [self.view addSubview:self->jokerShapeView];
        }
        [self->userSelectingCardView removeFromSuperview];
        [UIView beginAnimations:nil context:NULL];
        [UIView setAnimationDuration:0.2f];
        [UIView setAnimationDelegate:self];
        [UIView setAnimationRepeatAutoreverses:NO];
        [UIView setAnimationCurve:UIViewAnimationCurveLinear];
        [self->playingCardView[playerCard[self->justPreviouslySelected]] setFrame:CGRectMake((viewWidth - cardWidth)*0.5f, viewHeight*0.55f, cardWidth, cardHeight)];
        [self->playingCardView[playerCard[self->justPreviouslySelected]] setTag:0x20|self->justPreviouslySelected];
        for(i=0;i<numOfRemainedCards;i++){
            [self->playingCardView[playerCard[i] = [[mightyGame player:0] getCards:i]] setFrame:CGRectMake(viewWidth*(0.15625f+(CGFloat)i * 0.0625f+(CGFloat)self->submittingCount * 0.03125f), userCardYPosition[0], cardWidth, cardHeight)];
            [self->playingCardView[playerCard[i]] setTag:0x20|i];
        }
        if((self->currentPlayer+1)%5 == self->starter){
            [UIView setAnimationDidStopSelector:@selector(pauseBeforeDeterminingWinner)];
        }
        else{
            [UIView setAnimationDidStopSelector:@selector(submittingCard)];
        }
        [UIView commitAnimations];
    }
    else{
        if((self->currentPlayer != self->starter) && (self->isJokerCall)){
            if([[mightyGame player:self->currentPlayer] jokercallForAI]){
                self->submitCardNumber[self->currentPlayer] = submitCard = 52;
            }
            else{
                self->submitCardNumber[self->currentPlayer] = submitCard = [[mightyGame player:self->currentPlayer] submitCardForAI:self->starterShape JokerShape:&(self->jokerShape)];
            }
        }
        else{
            self->submitCardNumber[self->currentPlayer] = submitCard = [[mightyGame player:self->currentPlayer] submitCardForAI:self->starterShape JokerShape:&(self->jokerShape)];
        }
        [mightyGame submitCard:submitCard];
        if(self->currentPlayer == self->starter){
            switch(submitCard / 13){
                case 0:
                    self->starterShape = CLOVER;
                    break;
                case 1:
                    self->starterShape = HEART;
                    break;
                case 2:
                    self->starterShape = DIAMOND;
                    break;
                case 3:
                    self->starterShape = SPADE;
                    break;
                case 4:
                    self->starterShape = self->jokerShape;
                    switch(self->jokerShape){
                        case SPADE:
                            [self->jokerShapeLabel setText:@"Spade"];
                            break;
                        case DIAMOND:
                            [self->jokerShapeLabel setText:@"Diamond"];
                            break;
                        case HEART:
                            [self->jokerShapeLabel setText:@"Heart"];
                            break;
                        case CLOVER:
                            [self->jokerShapeLabel setText:@"Clover"];
                            break;
                        case ANYCARD:
                            [self->jokerShapeLabel setText:@"Any Card"];
                            break;
                    }
                    switch(self->currentPlayer){
                        case 1:
                            [self->jokerShapeView setFrame:CGRectMake(viewWidth*0.1f, viewHeight*0.5f, jokerShapeViewWidth, jokerShapeViewHeight)];
                            break;
                        case 2:
                            [self->jokerShapeView setFrame:CGRectMake(viewWidth*0.15f, viewHeight*0.3f, jokerShapeViewWidth, jokerShapeViewHeight)];
                            break;
                        case 3:
                            [self->jokerShapeView setFrame:CGRectMake(viewWidth*0.55f, viewHeight*0.3f, jokerShapeViewWidth, jokerShapeViewHeight)];
                            break;
                        case 4:
                            [self->jokerShapeView setFrame:CGRectMake(viewWidth*0.6f, viewHeight*0.5f, jokerShapeViewWidth, jokerShapeViewHeight)];
                            break;
                    }
                    [self.view addSubview:self->jokerShapeView];
                    break;
            }
        }
        switch(self->currentPlayer){
            case 1:
                [self->cardBackView[10 - self->submittingCount] removeFromSuperview];
                [self->playingCardView[submitCard] setFrame:CGRectMake(viewWidth*0.1f - cardWidth*0.5f,viewHeight*0.35f,cardWidth,cardHeight)];
                [self.view addSubview:self->playingCardView[submitCard]];
                [UIView beginAnimations:nil context:NULL];
                [UIView setAnimationDuration:0.2f];
                [UIView setAnimationDelegate:self];
                [UIView setAnimationRepeatAutoreverses:NO];
                [UIView setAnimationCurve:UIViewAnimationCurveLinear];
                [self->playingCardView[submitCard] setFrame:CGRectMake(viewWidth*0.3f - cardWidth*0.5f,viewHeight*0.35f,cardWidth,cardHeight)];
                if((self->currentPlayer+1)%5 == self->starter){
                    [UIView setAnimationDidStopSelector:@selector(pauseBeforeDeterminingWinner)];
                }
                else{
                    [UIView setAnimationDidStopSelector:@selector(submittingCard)];
                }
                [UIView commitAnimations];
                break;
            case 2:
                [self->cardBackView[20 - self->submittingCount] removeFromSuperview];
                [self->playingCardView[submitCard] setFrame:CGRectMake(viewWidth*0.2f - cardWidth*0.5f,viewHeight*0.04f,cardWidth,cardHeight)];
                [self.view addSubview:self->playingCardView[submitCard]];
                [UIView beginAnimations:nil context:NULL];
                [UIView setAnimationDuration:0.2f];
                [UIView setAnimationDelegate:self];
                [UIView setAnimationRepeatAutoreverses:NO];
                [UIView setAnimationCurve:UIViewAnimationCurveLinear];
                [self->playingCardView[submitCard] setFrame:CGRectMake(viewWidth*0.4f - cardWidth*0.5f,viewHeight*0.2f,cardWidth,cardHeight)];
                if((self->currentPlayer+1)%5 == self->starter){
                    [UIView setAnimationDidStopSelector:@selector(pauseBeforeDeterminingWinner)];
                }
                else{
                    [UIView setAnimationDidStopSelector:@selector(submittingCard)];
                }
                [UIView commitAnimations];
                break;
            case 3:
                [self->cardBackView[30 - self->submittingCount] removeFromSuperview];
                [self->playingCardView[submitCard] setFrame:CGRectMake(viewWidth*0.8f - cardWidth*0.5f,viewHeight*0.04f,cardWidth,cardHeight)];
                [self.view addSubview:self->playingCardView[submitCard]];
                [UIView beginAnimations:nil context:NULL];
                [UIView setAnimationDuration:0.2f];
                [UIView setAnimationDelegate:self];
                [UIView setAnimationRepeatAutoreverses:NO];
                [UIView setAnimationCurve:UIViewAnimationCurveLinear];
                [self->playingCardView[submitCard] setFrame:CGRectMake(viewWidth*0.6f - cardWidth*0.5f,viewHeight*0.2f,cardWidth,cardHeight)];
                if((self->currentPlayer+1)%5 == self->starter){
                    [UIView setAnimationDidStopSelector:@selector(pauseBeforeDeterminingWinner)];
                }
                else{
                    [UIView setAnimationDidStopSelector:@selector(submittingCard)];
                }
                [UIView commitAnimations];
                break;
            case 4:
                [self->cardBackView[40 - self->submittingCount] removeFromSuperview];
                [self->playingCardView[submitCard] setFrame:CGRectMake(viewWidth*0.9f - cardWidth*0.5f,viewHeight*0.35f,cardWidth,cardHeight)];
                [self.view addSubview:self->playingCardView[submitCard]];
                [UIView beginAnimations:nil context:NULL];
                [UIView setAnimationDuration:0.2f];
                [UIView setAnimationDelegate:self];
                [UIView setAnimationRepeatAutoreverses:NO];
                [UIView setAnimationCurve:UIViewAnimationCurveLinear];
                [self->playingCardView[submitCard] setFrame:CGRectMake(viewWidth*0.7f - cardWidth*0.5f,viewHeight*0.35f,cardWidth,cardHeight)];
                if((self->currentPlayer+1)%5 == self->starter){
                    [UIView setAnimationDidStopSelector:@selector(pauseBeforeDeterminingWinner)];
                }
                else{
                    [UIView setAnimationDidStopSelector:@selector(submittingCard)];
                }
                [UIView commitAnimations];
                break;
        }
        
    }
    if((self->friendCard & 0xf00) == 0x100){
        if((self->friendCard & 0xff) == (NSInteger)submitCard){
            mightyGame.knownChingoo = self->currentPlayer;
            [self->nameTagView[mightyGame.knownChingoo].layer setBorderWidth:3.0f];
            [self->nameTagView[mightyGame.knownChingoo].layer setBorderColor:[[UIColor orangeColor] CGColor]];
            for(i=0;i<[mightyGame player:mightyGame.knownChingoo].obtainedScore;i++){
                [self->playingCardView[[[mightyGame player:mightyGame.knownChingoo] getObtainedCard:i]] removeFromSuperview];
            }
        }
    }
    if(submitCard == 52)    self->isJokerShown = YES;
    self->currentPlayer++;
    self->currentPlayer %= 5;
}

-(void) pauseBeforeDeterminingWinner{
    [NSTimer scheduledTimerWithTimeInterval:0.7 target:self selector:@selector(deteminingWinner) userInfo:nil repeats:NO];
}

-(void) deteminingWinner{
    NSInteger i=0;
    NSInteger j=0;
    NSInteger winnersObtainedScore = 0;
    NSInteger girudaNumber = 0;
    NSInteger starterShapeNumber = 0;
    NSInteger obtainedImageOffset = 0;
    winner = 0;
    switch([mightyGame giruda]){
        case SPADE:
            girudaNumber = 3;
            break;
        case DIAMOND:
            girudaNumber = 2;
            break;
        case HEART:
            girudaNumber = 1;
            break;
        case CLOVER:
            girudaNumber = 0;
            break;
    }
    switch(self->starterShape){
        case SPADE:
            starterShapeNumber = 3;
            break;
        case DIAMOND:
            starterShapeNumber = 2;
            break;
        case HEART:
            starterShapeNumber = 1;
            break;
        case CLOVER:
            starterShapeNumber = 0;
            break;
        case ANYCARD:
            starterShapeNumber = 4;
    }
    if([mightyGame giruda] != NOGIRUDA){
        for(i=1;i<5;i++){
            if((([mightyGame giruda] != SPADE) && (self->submitCardNumber[winner] == 51)) || (([mightyGame giruda] == SPADE) && (self->submitCardNumber[winner] == 38))){
                break;
            }
            else if((([mightyGame giruda] != SPADE) && (self->submitCardNumber[i] == 51)) || (([mightyGame giruda] == SPADE) && (self->submitCardNumber[i] == 38))){
                winner = i;
                break;
            }
            else if(self->submitCardNumber[winner] == 52){
                if((self->submittingCount == 1) || (self->submittingCount == 10) || self->isJokerCall)  winner = i;
                continue;
            }
            else if(self->submitCardNumber[i] == 52){
                if((self->submittingCount != 1) && (self->submittingCount != 10) && (!self->isJokerCall))  winner = i;
                continue;
            }
            else if((self->submitCardNumber[winner] / 13 == girudaNumber) && (self->submitCardNumber[i] / 13 != girudaNumber)){
                continue;
            }
            else if((self->submitCardNumber[winner] / 13 != girudaNumber) && (self->submitCardNumber[i] / 13 == girudaNumber)){
                winner = i;
                continue;
            }
            else if((self->submitCardNumber[winner] / 13 == girudaNumber) && (self->submitCardNumber[i] / 13 == girudaNumber)){
                if(self->submitCardNumber[i] > self->submitCardNumber[winner])  winner = i;
                continue;
            }
            else if((self->submitCardNumber[winner] / 13 == starterShapeNumber) && (self->submitCardNumber[i] / 13 != starterShapeNumber)){
                continue;
            }
            else if((self->submitCardNumber[winner] / 13 != starterShapeNumber) && (self->submitCardNumber[i] / 13 == starterShapeNumber)){
                winner = i;
                continue;
            }
            else if((self->submitCardNumber[winner] / 13 == starterShapeNumber) && (self->submitCardNumber[i] / 13 == starterShapeNumber)){
                if(self->submitCardNumber[i] > self->submitCardNumber[winner])  winner = i;
                continue;
            }
        }
    }
    else{
        for(i=1;i<5;i++){
            if(self->submitCardNumber[winner] == 51){
                break;
            }
            else if(self->submitCardNumber[i] == 51){
                winner = i;
                break;
            }
            else if(self->submitCardNumber[winner] == 52){
                if((self->submittingCount == 1) || (self->submittingCount == 10))  winner = i;
                continue;
            }
            else if(self->submitCardNumber[i] == 52){
                if((self->submittingCount != 1) && (self->submittingCount != 10))  winner = i;
                continue;
            }
            else if((self->submitCardNumber[winner] / 13 == starterShapeNumber) && (self->submitCardNumber[i] / 13 != starterShapeNumber)){
                continue;
            }
            else if((self->submitCardNumber[winner] / 13 != starterShapeNumber) && (self->submitCardNumber[i] / 13 == starterShapeNumber)){
                winner = i;
                continue;
            }
            else if((self->submitCardNumber[winner] / 13 == starterShapeNumber) && (self->submitCardNumber[i] / 13 == starterShapeNumber)){
                if(self->submitCardNumber[i] > self->submitCardNumber[winner])  winner = i;
                continue;
            }
        }
    }
    if((self->submittingCount == 1) && (self->friendCard == 0x300) && (winner != self->starter)){
        [self->nameTagView[winner].layer setBorderColor:[[UIColor orangeColor] CGColor]];
        [self->nameTagView[winner].layer setBorderWidth:3.0f];
        mightyGame.knownChingoo = winner;
    }
    if((winner == [mightyGame declarer]) || (winner == [mightyGame knownChingoo])){
        [self removeNonPointCards];
    }
    else{
        for(i=0, winnersObtainedScore=0;i<5;i++){
            if(self->submitCardNumber[i] % 13 >= 8){
                winnersObtainedScore++;
            }
        }
        if(winnersObtainedScore > 0){
            obtainedImageOffset = [mightyGame player:winner].obtainedScore;
            [UIView beginAnimations:nil context:NULL];
            [UIView setAnimationDuration:0.4f];
            [UIView setAnimationDelegate:self];
            [UIView setAnimationRepeatAutoreverses:NO];
            [UIView setAnimationCurve:UIViewAnimationCurveLinear];
            if(winner == 0){
                for(i=0, j=0;i<5;i++){
                    if(self->submitCardNumber[i] % 13 >= 8){
                        [playingCardView[self->submitCardNumber[i]] setFrame:CGRectMake(obtainedCardXPosition[0]+obtainedCardWidth*(CGFloat)(obtainedImageOffset +j)*0.3f, obtainedCardYPosition[0],obtainedCardWidth, obtainedCardHeight)];
                        [self.view bringSubviewToFront:playingCardView[self->submitCardNumber[i]]];
                        [[mightyGame player:0] addObtainedCard:self->submitCardNumber[i]];
                        j++;
                    }
                }
            }
            else{
                for(i=0, j=0;i<5;i++){
                    if(self->submitCardNumber[i] % 13 >= 8){
                        if(obtainedImageOffset + j >= 10){
                            [playingCardView[self->submitCardNumber[i]] setFrame:CGRectMake(obtainedCardXPosition[winner]+obtainedCardWidth*(CGFloat)(obtainedImageOffset+j-10)*0.3f, obtainedCardYPosition[winner] + obtainedCardHeight * 0.25f,obtainedCardWidth, obtainedCardHeight)];
                        }
                        else{
                            [playingCardView[self->submitCardNumber[i]] setFrame:CGRectMake(obtainedCardXPosition[winner]+obtainedCardWidth*(CGFloat)(obtainedImageOffset +j)*0.3f, obtainedCardYPosition[winner],obtainedCardWidth, obtainedCardHeight)];
                        }
                        [self.view bringSubviewToFront:playingCardView[self->submitCardNumber[i]]];
                        [[mightyGame player:winner] addObtainedCard:self->submitCardNumber[i]];
                        j++;
                    }
                }
            }
            [UIView setAnimationDidStopSelector:@selector(removeNonPointCards)];
            [UIView commitAnimations];
        }
        else{
            [self removeNonPointCards];
        }
    }
}

-(void) removeNonPointCards{
    NSInteger i=0;
    if((winner == [mightyGame declarer]) || (winner == [mightyGame knownChingoo])){
        for(i=0;i<5;i++){
            [playingCardView[self->submitCardNumber[i]] removeFromSuperview];
        }
    }
    else{
        for(i=0;i<5;i++){
            if(self->submitCardNumber[i] % 13 < 8){
                [playingCardView[self->submitCardNumber[i]] removeFromSuperview];
            }
        }
    }
    if(self->submittingCount == 10) [self gameSet];
    else                            [self Turns];
    [self->jokerShapeView removeFromSuperview];
}

-(void) viewInitializaion{
    NSInteger i=0;
    viewWidth = [[UIScreen mainScreen] bounds].size.width;
    viewHeight = [[UIScreen mainScreen] bounds].size.height;
    borderForUser14And23 = viewHeight * 0.28f;
    borderForMainPlayerAnd14 = viewHeight * 0.72f;
    borderForUser23 = viewWidth * 0.5f;
    borderForUser1 = viewWidth * 0.3f;
    borderForUser4 = viewWidth * 0.7f;
    
    cardWidth = viewWidth / 8.0f;
    cardHeight = cardWidth * 4.0f / 3.0f;
    cardColSpace = cardWidth * 0.5f;
    cardRowSpace = cardHeight / 8.0f;
    cardBackSpace = cardWidth / 6.0f;
    nameTagLabelWidth = viewWidth * 0.16f;
    nameTagLabelHeight = viewHeight * 0.03f;
    
    mainPlayerCardAfterReceivingThreeCardsXPosition = viewWidth * 0.09375f;
    userCardXPosition[0] = viewWidth*0.15625f;
    userCardYPosition[0] = viewHeight * 0.85f;
    userCardXPosition[1] = (borderForUser1 - cardHeight) * 0.5f;
    userCardYPosition[1] = viewHeight * 0.36f;
    userCardXPosition[2] = (borderForUser23 - cardWidth - cardBackSpace * 9.0f) * 0.5f;
    userCardYPosition[2] = viewHeight * 0.045f;
    userCardXPosition[3] = (borderForUser23 + viewWidth - cardWidth - cardBackSpace * 9.0f) * 0.5f;
    userCardYPosition[3] = viewHeight * 0.045f;
    userCardXPosition[4] = (borderForUser4 + viewWidth - cardHeight) * 0.5f;
    userCardYPosition[4] = viewHeight * 0.36f;
    gapOfYPositionOfChosenCard = viewHeight * 0.03f;
    
    obtainedCardWidth = cardWidth * 0.6f;
    obtainedCardHeight = cardHeight * 0.6f;
    
    obtainedCardXPosition[0] = viewWidth * 0.5f - (obtainedCardWidth * 6.7f) * 0.5f;
    obtainedCardYPosition[0] = userCardYPosition[0] - obtainedCardHeight - viewHeight * 0.04f;
    obtainedCardXPosition[1] = borderForUser1 * 0.5f - (obtainedCardWidth * 3.7f) * 0.5f;
    obtainedCardYPosition[1] = borderForMainPlayerAnd14 - obtainedCardHeight * 1.25f - viewHeight * 0.02f;
    obtainedCardXPosition[2] = borderForUser23 * 0.5f - (obtainedCardWidth * 3.7f) * 0.5f;
    obtainedCardYPosition[2] = borderForUser14And23 - obtainedCardHeight * 1.25f - viewHeight * 0.01f;
    obtainedCardXPosition[3] = (borderForUser23 + viewWidth) * 0.5f - (obtainedCardWidth * 3.7f) * 0.5f;
    obtainedCardYPosition[3] = borderForUser14And23 - obtainedCardHeight * 1.25f - viewHeight * 0.01f;
    obtainedCardXPosition[4] = (borderForUser4 + viewWidth) * 0.5f - (obtainedCardWidth * 3.7f) * 0.5f;
    obtainedCardYPosition[4] = borderForMainPlayerAnd14 - obtainedCardHeight * 1.25f - viewHeight * 0.02f;
    
    nameTagPositionForPlayer = CGRectMake((obtainedCardXPosition[0]-nameTagLabelWidth) * 0.5f, obtainedCardYPosition[0] + obtainedCardHeight * 0.5f - nameTagLabelHeight * 0.5f,nameTagLabelWidth,nameTagLabelHeight);
    nameTagPositionForUser1 = CGRectMake(viewWidth*0.15f-nameTagLabelWidth*0.5f,viewHeight*0.3f,nameTagLabelWidth,nameTagLabelHeight);
    nameTagPositionForUser2 = CGRectMake(viewWidth*0.25f-nameTagLabelWidth*0.5f,viewHeight*0.01f,nameTagLabelWidth,nameTagLabelHeight);
    nameTagPositionForUser3 = CGRectMake(viewWidth*0.75f-nameTagLabelWidth*0.5f,viewHeight*0.01f,nameTagLabelWidth,nameTagLabelHeight);
    nameTagPositionForUser4 = CGRectMake(viewWidth*0.85f-nameTagLabelWidth*0.5f,viewHeight*0.3f,nameTagLabelWidth,nameTagLabelHeight);

    jokerShapeViewWidth = viewWidth * 0.3f;
    jokerShapeViewHeight = viewHeight * 0.1f;
    declarerInfoViewWidth = viewWidth * 0.25f;
    declarerInfoViewHeight = viewWidth * 0.05f;
    
    self->submittingCount = 0;
    
    for(i=0;i<53;i++){
        self->playingCardView[i] = [[UIButton alloc] init];
        [self->playingCardView[i] setBackgroundImage:[UIImage imageNamed:getFileName(i)] forState:UIControlStateNormal];
        [self->playingCardView[i] addTarget:self action:@selector(actionForChoosingCard:) forControlEvents:UIControlEventTouchUpInside];
        [self->playingCardView[i] setTag:1024];
        
    }
    self->cardBackView[0] = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"b1fh.png"]];
    [self->cardBackView[0] setFrame:CGRectMake(userCardXPosition[1],userCardYPosition[1],cardHeight,cardWidth)];
    self->cardBackView[10] = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"b1fv.png"]];
    [self->cardBackView[10] setFrame:CGRectMake(userCardXPosition[2],userCardYPosition[2],cardWidth,cardHeight)];
    self->cardBackView[20] = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"b1fv.png"]];
    [self->cardBackView[20] setFrame:CGRectMake(userCardXPosition[3],userCardYPosition[3],cardWidth,cardHeight)];
    self->cardBackView[30] = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"b1fh.png"]];
    [self->cardBackView[30] setFrame:CGRectMake(userCardXPosition[4],userCardYPosition[4],cardHeight,cardWidth)];
    self->cardBackView[40] = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"b1fv.png"]];
    [self->cardBackView[40] setFrame:CGRectMake(viewWidth*0.5f - cardWidth*1.25f,userCardYPosition[0],cardWidth,cardHeight)];
    for(i=1;i<10;i++){
        self->cardBackView[i] = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"b1pb.png"]];
        [self->cardBackView[i] setFrame:CGRectMake(userCardXPosition[1],userCardYPosition[1]+cardWidth+(float)(i-1)*cardBackSpace, cardHeight, cardBackSpace)];
        self->cardBackView[i+10] = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"b1pr.png"]];
        [self->cardBackView[i+10] setFrame:CGRectMake(userCardXPosition[2] + cardWidth + (float)(i-1) * cardBackSpace, userCardYPosition[2], cardBackSpace, cardHeight)];
        self->cardBackView[i+20] = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"b1pr.png"]];
        [self->cardBackView[i+20] setFrame:CGRectMake(userCardXPosition[3] + cardWidth + (float)(i-1) * cardBackSpace, userCardYPosition[3], cardBackSpace, cardHeight)];
        self->cardBackView[i+30] = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"b1pb.png"]];
        [self->cardBackView[i+30] setFrame:CGRectMake(userCardXPosition[4],userCardYPosition[4]+cardWidth+(float)(i-1)*cardBackSpace, cardHeight, cardBackSpace)];
        self->cardBackView[i+40] = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"b1pr.png"]];
        [self->cardBackView[i+40] setFrame:CGRectMake(viewWidth*0.5f - cardWidth*0.25f + cardBackSpace*(float)(i-1),userCardYPosition[0], cardBackSpace,cardHeight)];
    }
    self->dealedCardBackView = [[UIImageView alloc] init];
    self->cardBackHorizontalImage = [[NSArray alloc] initWithObjects:
                                     [UIImage imageNamed:@"b1fh.png"],
                                     [UIImage imageNamed:@"b2fh.png"],
                                     [UIImage imageNamed:@"b3fh.png"],
                                     [UIImage imageNamed:@"b4fh.png"],nil];

    self->cardBackVerticalImage = [[NSArray alloc] initWithObjects:
                                     [UIImage imageNamed:@"b1fv.png"],
                                     [UIImage imageNamed:@"b2fv.png"],
                                     [UIImage imageNamed:@"b3fv.png"],
                                     [UIImage imageNamed:@"b4fv.png"],nil];
    for(i=0;i<4;i++){
        self->cardStartingPointVirtical[i] = CGRectMake(viewWidth*0.5f - cardWidth*0.5f , viewHeight*0.5f - cardHeight*0.5f,cardWidth+cardBackSpace*(CGFloat)i, cardHeight);
        self->cardStartingPointHorizontal[i] = CGRectMake(viewWidth*0.5f - cardHeight*0.5f , viewHeight*0.5f - cardWidth*0.5f,cardHeight, cardWidth+cardBackSpace*(CGFloat)i);
        self->cardDestination[0][i] = CGRectMake(viewWidth*0.5f - cardWidth*0.5f , viewHeight*0.8f,cardWidth+cardBackSpace*(CGFloat)i, cardHeight);
        self->cardDestination[1][i] = CGRectMake(viewWidth*0.1f - cardHeight*0.5f,viewHeight*0.5f - cardHeight*0.5f,cardHeight, cardWidth+cardBackSpace*(CGFloat)i);
        self->cardDestination[2][i] = CGRectMake(viewWidth*0.125f,viewHeight*0.04f,cardWidth+cardBackSpace*(CGFloat)i, cardHeight);
        self->cardDestination[3][i] = CGRectMake(viewWidth*0.5625,viewHeight*0.04f,cardWidth+cardBackSpace*(CGFloat)i, cardHeight);
        self->cardDestination[4][i] = CGRectMake(viewWidth*0.9f - cardHeight*0.5f,viewHeight*0.5f - cardHeight*0.5f, cardHeight, cardWidth+cardBackSpace*(CGFloat)i);
    }
    
    nameTagView[0] = [[UILabel alloc] initWithFrame:nameTagPositionForPlayer];
    nameTagView[1] = [[UILabel alloc] initWithFrame:nameTagPositionForUser1];
    nameTagView[2] = [[UILabel alloc] initWithFrame:nameTagPositionForUser2];
    nameTagView[3] = [[UILabel alloc] initWithFrame:nameTagPositionForUser3];
    nameTagView[4] = [[UILabel alloc] initWithFrame:nameTagPositionForUser4];
    
    for(i=0;i<5;i++){
        [nameTagView[i] setTextColor:[UIColor whiteColor]];
        nameTagView[i].textAlignment = NSTextAlignmentCenter;
        nameTagView[i].font = [UIFont systemFontOfSize:12.0f];
    }
    deckBackView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"deck.png"]];
    [deckBackView setFrame:CGRectMake(viewWidth*0.5f - cardWidth*0.5f , viewHeight*0.5f - cardHeight*0.5f,cardHeight*114.0f/96.0f, cardWidth*98.0f/71.0f)];
    
    self->declarerInfoView = [[UIView alloc] init];
    self->girudaAtDeclarerInfo = [[UIImageView alloc] initWithFrame:CGRectMake(0.0f,0.0f,declarerInfoViewWidth*0.2f, declarerInfoViewHeight)];
    self->pledgeAtDeclarerInfo = [[UILabel alloc] initWithFrame:CGRectMake(declarerInfoViewWidth*0.2f, 0.0f, declarerInfoViewWidth*0.2f, declarerInfoViewHeight)];
    self->friendAtDeclarerInfo = [[UILabel alloc] initWithFrame:CGRectMake(declarerInfoViewWidth*0.4f, 0.0f, declarerInfoViewWidth*0.6f, declarerInfoViewHeight)];
    [self->declarerInfoView setBackgroundColor:[UIColor colorWithRed:0.1f green:0.508f blue:0.1f alpha:1.0f]];
    [self->declarerInfoView.layer setBorderWidth:2.0f];
    [self->declarerInfoView.layer setBorderColor:[[UIColor colorWithRed:0.15f green:0.762f blue:0.15f alpha:1.0f] CGColor]];
    [self->pledgeAtDeclarerInfo setTextColor:[UIColor whiteColor]];
    self->pledgeAtDeclarerInfo.textAlignment = NSTextAlignmentCenter;
    self->pledgeAtDeclarerInfo.font = [UIFont systemFontOfSize:15.0f];
    [self->friendAtDeclarerInfo setTextColor:[UIColor whiteColor]];
    self->friendAtDeclarerInfo.textAlignment = NSTextAlignmentCenter;
    self->friendAtDeclarerInfo.font = [UIFont systemFontOfSize:15.0f];
    [self->declarerInfoView addSubview:self->girudaAtDeclarerInfo];
    [self->declarerInfoView addSubview:self->pledgeAtDeclarerInfo];
    [self->declarerInfoView addSubview:self->friendAtDeclarerInfo];
    
    self->jokerShapeView = [[UIView alloc] init];
    self->jokerShapeLabel = [[UILabel alloc] initWithFrame:CGRectMake(0.0f,0.0f,jokerShapeViewWidth, jokerShapeViewHeight)];
    [self->jokerShapeView setBackgroundColor:[UIColor colorWithRed:0.05f green:0.254f blue:0.05f alpha:0.6f]];
    [self->jokerShapeLabel setTextColor:[UIColor whiteColor]];
    self->jokerShapeLabel.textAlignment = NSTextAlignmentCenter;
    self->jokerShapeLabel.font = [UIFont systemFontOfSize:25.0f];
    [self->jokerShapeView addSubview:self->jokerShapeLabel];
    
    self->borderView = [[UIImageView alloc] initWithFrame:CGRectMake(0.0f,0.0f,viewWidth,viewHeight)];
    [self.view addSubview:self->borderView];
    UIGraphicsBeginImageContext(self->borderView.frame.size);
    [self->borderView.image drawInRect:CGRectMake(0.0f, 0.0f, self->borderView.frame.size.width,self->borderView.frame.size.height)];
    CGContextSetLineCap(UIGraphicsGetCurrentContext(), kCGLineCapRound);
    CGContextBeginPath(UIGraphicsGetCurrentContext());
    CGContextSetLineWidth(UIGraphicsGetCurrentContext(), 3.0f);
    CGContextSetRGBStrokeColor(UIGraphicsGetCurrentContext(), 0.3f, 0.8f, 0.3f, 1.0f);
    CGContextSetBlendMode(UIGraphicsGetCurrentContext(), kCGBlendModeNormal);
    CGContextMoveToPoint(UIGraphicsGetCurrentContext(), 0.0f, borderForUser14And23);
    CGContextAddLineToPoint(UIGraphicsGetCurrentContext(), viewWidth, borderForUser14And23);
    CGContextMoveToPoint(UIGraphicsGetCurrentContext(), borderForUser23, 0.0f);
    CGContextAddLineToPoint(UIGraphicsGetCurrentContext(), borderForUser23, borderForUser14And23);
    CGContextMoveToPoint(UIGraphicsGetCurrentContext(), borderForUser1, borderForUser14And23);
    CGContextAddLineToPoint(UIGraphicsGetCurrentContext(), borderForUser1, borderForMainPlayerAnd14);
    CGContextMoveToPoint(UIGraphicsGetCurrentContext(), borderForUser4, borderForUser14And23);
    CGContextAddLineToPoint(UIGraphicsGetCurrentContext(), borderForUser4, borderForMainPlayerAnd14);
    CGContextMoveToPoint(UIGraphicsGetCurrentContext(), 0.0f, borderForMainPlayerAnd14);
    CGContextAddLineToPoint(UIGraphicsGetCurrentContext(), viewWidth, borderForMainPlayerAnd14);
    CGContextStrokePath(UIGraphicsGetCurrentContext());
    
    CGContextSetLineWidth(UIGraphicsGetCurrentContext(), 1.0f);
    CGContextSetRGBStrokeColor(UIGraphicsGetCurrentContext(), 0.2f, 0.4f, 0.2f, 1.0f);
    CGContextMoveToPoint(UIGraphicsGetCurrentContext(), obtainedCardXPosition[0], obtainedCardYPosition[0]);
    CGContextAddLineToPoint(UIGraphicsGetCurrentContext(), obtainedCardXPosition[0] + obtainedCardWidth * 6.7f, obtainedCardYPosition[0]);
    CGContextAddLineToPoint(UIGraphicsGetCurrentContext(), obtainedCardXPosition[0] + obtainedCardWidth * 6.7f, obtainedCardYPosition[0] + obtainedCardHeight);
    CGContextAddLineToPoint(UIGraphicsGetCurrentContext(), obtainedCardXPosition[0], obtainedCardYPosition[0] + obtainedCardHeight);
    CGContextAddLineToPoint(UIGraphicsGetCurrentContext(), obtainedCardXPosition[0], obtainedCardYPosition[0]);
    
    for(i=1;i<5;i++){
        CGContextMoveToPoint(UIGraphicsGetCurrentContext(), obtainedCardXPosition[i], obtainedCardYPosition[i]);
        CGContextAddLineToPoint(UIGraphicsGetCurrentContext(), obtainedCardXPosition[i] + obtainedCardWidth * 3.7f, obtainedCardYPosition[i]);
        CGContextAddLineToPoint(UIGraphicsGetCurrentContext(), obtainedCardXPosition[i] + obtainedCardWidth * 3.7f, obtainedCardYPosition[i] + obtainedCardHeight * 1.25f);
        CGContextAddLineToPoint(UIGraphicsGetCurrentContext(), obtainedCardXPosition[i], obtainedCardYPosition[i] + obtainedCardHeight * 1.25f);
        CGContextAddLineToPoint(UIGraphicsGetCurrentContext(), obtainedCardXPosition[i], obtainedCardYPosition[i]);
    }
    CGContextStrokePath(UIGraphicsGetCurrentContext());
    self->borderView.image = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
}


/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
