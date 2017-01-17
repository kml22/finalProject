#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController
@synthesize soloMighty;
@synthesize networkMighty;
@synthesize tutorialButton;
@synthesize settingButton;
@synthesize soloGameView;

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    CGRect mightyViewSize = [[UIScreen mainScreen] bounds];
    viewWidth = mightyViewSize.size.width;
    viewHeight = mightyViewSize.size.height;
    
    
    [self.view setBackgroundColor:[UIColor colorWithRed:1.0f green:1.0f blue:1.0f alpha:1.0f]];
    UILabel *title = [[UILabel alloc] initWithFrame:CGRectMake(viewWidth*0.5f-150.0f,10.0f,300.0f,50.0f)];
    
    soloMighty = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.17f,viewHeight*0.55f,viewWidth*0.25f,viewHeight*0.12f)];
    networkMighty = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.57f,viewHeight*0.55f,viewWidth*0.25f,viewHeight*0.12f)];
    tutorialButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.17f,viewHeight*0.65f,viewWidth*0.25f,viewHeight*0.12f)];
    settingButton = [[UIButton alloc] initWithFrame:CGRectMake(viewWidth*0.57f,viewHeight*0.65f,viewWidth*0.25f,viewHeight*0.12f)];
    
    [title setText:@"Mighty"];
    title.textAlignment = NSTextAlignmentCenter;
    
    [soloMighty setTitle:@"1인용 마이티" forState:UIControlStateNormal];
    [soloMighty addTarget:self action:@selector(showSoloGameView) forControlEvents:UIControlEventTouchUpInside];
    [soloMighty setBackgroundColor:[UIColor whiteColor]];
    [soloMighty setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [soloMighty setFont:[UIFont systemFontOfSize:12]];
    
    [networkMighty setTitle:@"네트워크 마이티" forState:UIControlStateNormal];
    [networkMighty setBackgroundColor:[UIColor whiteColor]];
    [networkMighty setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [networkMighty setFont:[UIFont systemFontOfSize:12]];
    
    [tutorialButton setTitle:@"튜토리얼" forState:UIControlStateNormal];
    [tutorialButton setBackgroundColor:[UIColor whiteColor]];
    [tutorialButton setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [tutorialButton setFont:[UIFont systemFontOfSize:12]];
    
    [settingButton setTitle:@"설정" forState:UIControlStateNormal];
    [settingButton setBackgroundColor:[UIColor whiteColor]];
    [settingButton setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [settingButton setFont:[UIFont systemFontOfSize:12]];
    
    [self.view addSubview:title];
    [self.view addSubview:soloMighty];
    [self.view addSubview:networkMighty];
    [self.view addSubview:tutorialButton];
    [self.view addSubview:settingButton];

}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)showSoloGameView
{
    CGRect viewSize = [[UIScreen mainScreen] bounds];

    self->soloGameView = [[soloGameViewController alloc]init];
    [self->soloGameView.view setFrame:CGRectMake(0.0f,0.0f,viewSize.size.width,viewSize.size.height)];
    [self.view addSubview:self->soloGameView.view]; //하얀화면위에 
}

@end
