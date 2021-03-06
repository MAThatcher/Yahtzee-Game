import  java.awt.*;
import  java.awt.event.*;
import  javax.swing.*;
import java.util.Arrays;

public class Yahtzee extends JFrame{
  private Die[] dice = new Die[5];
  private JButton[] eastButtons = new JButton[10];
  private JButton[] westButtons = new JButton[10];
  private JButton[] diceSelectButtons = new JButton[5];
  private JButton rollButton = new JButton("Roll");
  private JLabel totalScoreLabel;
  private JLabel roundLabel;
  private JLabel rollNumLabel;
  private JLabel[] eastLabels = new JLabel[10];
  private JLabel[] westLabels = new JLabel[10];
  private JLabel[] diceLabels = new JLabel[5];
  private JPanel mainPanel = new JPanel();
  private JPanel northPanel = new JPanel();//Title Panel
  private JPanel eastPanel = new JPanel(); //Lower Section Score
  private JPanel centerPanel = new JPanel();// Game Panel
  private JPanel westPanel = new JPanel(); //Upper Section Score
  private JPanel southPanel = new JPanel(); // Buttons Panel
  private int totalScore = 0, roll = 0, round = 1, bonusScore = 0;
  
  
  //constructor
  public Yahtzee(){
    this.setSize(1920,1080);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Yahtzee!");
    
    mainPanel.setLayout(new BorderLayout());
    northPanel.setLayout(new GridLayout());
    eastPanel.setLayout(new GridLayout(10,4,10,10));
    centerPanel.setLayout(new GridLayout(12,5,10,10));
    westPanel.setLayout(new GridLayout(10,4,10,10));
    southPanel.setLayout(new GridLayout());
    
    northPanel.setBorder (BorderFactory.createLineBorder(Color.black));
    eastPanel.setBorder  (BorderFactory.createLineBorder(Color.black));
    centerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    westPanel.setBorder  (BorderFactory.createLineBorder(Color.black));
    southPanel.setBorder (BorderFactory.createLineBorder(Color.black));
    
    //North Panel
    northPanel.add(new JLabel("YAHTZEE!", JLabel.LEFT));
    roundLabel = new JLabel("Round " + String.valueOf(round) + "/13");
    northPanel.add(roundLabel);
    rollNumLabel = new JLabel("Roll " + String.valueOf(roll) + "/3");
    northPanel.add(rollNumLabel);
    northPanel.add(new JLabel("TotalScore: ", JLabel.RIGHT));
    totalScoreLabel = new JLabel(String.valueOf(totalScore), JLabel.CENTER);
    northPanel.add(totalScoreLabel);
    //End North Panel
    
    //East Panel, Upper Score Section
    for (int i = 0 ; i < eastLabels.length ; i++){
      eastLabels[i] = new JLabel("0 Points",JLabel.LEFT);
    } 
    
    for (int i = 0 ; i < eastButtons.length; i++){
      eastButtons[i] = new JButton("Select");
      eastButtons[i].setEnabled(false);
    }
    
    eastPanel.add(new JLabel("Score Card",JLabel.CENTER));
    eastPanel.add(new JLabel("How to Score",JLabel.CENTER));
    eastPanel.add(eastButtons[0]);
    eastPanel.add(new JLabel("Select Scoring Option",JLabel.CENTER));
    eastButtons[0].setVisible(false);
    eastPanel.add(new JLabel("3 Of A Kind",JLabel.CENTER));
    eastPanel.add(new JLabel("Add Total of all Dice",JLabel.CENTER));
    eastPanel.add(eastLabels[1]);
    eastPanel.add(eastButtons[1]);
    eastPanel.add(new JLabel("4 Of A Kind",JLabel.CENTER));
    eastPanel.add(new JLabel("Add Total of all Dice",JLabel.CENTER));
    eastPanel.add(eastLabels[2]);
    eastPanel.add(eastButtons[2]);
    eastPanel.add(new JLabel("Full House",JLabel.CENTER));
    eastPanel.add(new JLabel("Score 25",JLabel.CENTER));
    eastPanel.add(eastLabels[3]);
    eastPanel.add(eastButtons[3]);
    eastPanel.add(new JLabel("Sm. Straight",JLabel.CENTER));
    eastPanel.add(new JLabel("Score 30",JLabel.CENTER));
    eastPanel.add(eastLabels[4]);
    eastPanel.add(eastButtons[4]);
    eastPanel.add(new JLabel("Lg. Straight",JLabel.CENTER));
    eastPanel.add(new JLabel("Score 40",JLabel.CENTER));
    eastPanel.add(eastLabels[5]);
    eastPanel.add(eastButtons[5]);
    eastPanel.add(new JLabel("Yahtzee",JLabel.CENTER));
    eastPanel.add(new JLabel("Score 50",JLabel.CENTER));
    eastPanel.add(eastLabels[6]);
    eastPanel.add(eastButtons[6]);
    eastPanel.add(new JLabel("Chance",JLabel.CENTER));
    eastPanel.add(new JLabel("Add Total of all Dice",JLabel.CENTER));
    eastPanel.add(eastLabels[7]);
    eastPanel.add(eastButtons[7]);
    eastPanel.add(new JLabel("",JLabel.CENTER));
    eastPanel.add(new JLabel("",JLabel.CENTER));
    eastPanel.add(eastLabels[8]);
    eastPanel.add(eastButtons[8]);
    eastButtons[8].setVisible(false);
    eastLabels[8].setVisible(false);
    eastPanel.add(new JLabel("Number of Yahtzee's",JLabel.CENTER));
    eastPanel.add(new JLabel("100 for each bonus",JLabel.CENTER));
    eastPanel.add(eastLabels[9]);
    eastPanel.add(eastButtons[9]);
    eastLabels[9].setText( "0" );
    eastButtons[9].setVisible(false);
    
    //3 Of A Kind Button ActionListener
    eastButtons[1].addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0){
        eastButtons[1].setVisible(false);
        int scoreToAdd = 0;
        for (int i = 0 ; i < dice.length ; i++){
          scoreToAdd+= dice[i].getResult();
        }
        eastLabels[1].setText(String.valueOf(scoreToAdd) + " Points");
        totalScore += scoreToAdd;
        totalScoreLabel.setText(String.valueOf(totalScore));
        scoreSelected();
      }
    });
    
    //4 Of A Kind Button ActionListener
    eastButtons[2].addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0){
        eastButtons[2].setVisible(false);
        int scoreToAdd = 0;
        for (int i = 0 ; i < dice.length ; i++){
          scoreToAdd+= dice[i].getResult();
        }
        eastLabels[2].setText(String.valueOf(scoreToAdd) + " Points");
        totalScore += scoreToAdd;
        totalScoreLabel.setText(String.valueOf(totalScore));
        scoreSelected();
      }
    });
    
    //Full House Button ActionListener
    eastButtons[3].addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0){
        eastButtons[3].setVisible(false);
        eastLabels[3].setText("25 Points");
        totalScore += 25;
        totalScoreLabel.setText(String.valueOf(totalScore));
        scoreSelected();
      }
    });
    
    //Small Straight Button ActionListener
    eastButtons[4].addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0){
        eastButtons[4].setVisible(false);
        eastLabels[4].setText("30 Points");
        totalScore += 30;
        totalScoreLabel.setText(String.valueOf(totalScore));
        scoreSelected();
      }
    });
    
    //Large Straight Button ActionListener
    eastButtons[5].addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0){
        eastButtons[5].setVisible(false);
        eastLabels[5].setText("40 Points");
        totalScore += 40;
        totalScoreLabel.setText(String.valueOf(totalScore));
        scoreSelected();
      }
    });
    
    //Yahtzee Button ActionListener
    eastButtons[6].addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0){
        String scoreFromYahtzee = eastLabels[9].getText();
        int scoreToAdd = Integer.parseInt(scoreFromYahtzee)*100+50;
        eastLabels[9].setText(String.valueOf(Integer.parseInt(eastLabels[9].getText()) + 1));
        eastLabels[6].setText(String.valueOf(scoreToAdd) + " Points");
        if( scoreToAdd == 50){
          totalScore += scoreToAdd;
        }
        else{
          totalScore += 100;
        }
        totalScoreLabel.setText(String.valueOf(totalScore));
        scoreSelected();
      }
    });
    
    //Chance Button ActionListener
    eastButtons[7].addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0){
        eastButtons[7].setVisible(false);
        int scoreToAdd = 0;
        for (int i = 0 ; i < dice.length ; i++){
          scoreToAdd+= dice[i].getResult();
        }
        eastLabels[7].setText(String.valueOf(scoreToAdd) + " Points");
        totalScore += scoreToAdd;
        totalScoreLabel.setText(String.valueOf(totalScore));
        scoreSelected();
      }
    });
    //End east Panel
    
    //West Panel, Lower Score Section
    for (int i = 0 ; i < westLabels.length ; i++){
      westLabels[i] = new JLabel("0 Points",JLabel.CENTER);
    }
    for (int i = 0 ; i < westButtons.length; i++){
      westButtons[i] = new JButton("Select");
      westButtons[i].setEnabled(false);
    }
    
    westPanel.add(new JLabel("Score Card",JLabel.CENTER));
    westPanel.add(new JLabel("How to Score",JLabel.CENTER));
    westPanel.add(westLabels[0]);
    westPanel.add(new JLabel("Select Scoring Option",JLabel.CENTER));
    westLabels[0].setVisible(false);
    westPanel.add(new JLabel("Ace",JLabel.CENTER));
    westPanel.add(new JLabel("Count and Add Only Aces",JLabel.CENTER));
    westPanel.add(westLabels[1]);
    westPanel.add(westButtons[1]);
    westPanel.add(new JLabel("Twos",JLabel.CENTER));
    westPanel.add(new JLabel("Count and Add Only Twos",JLabel.CENTER));
    westPanel.add(westLabels[2]);
    westPanel.add(westButtons[2]);
    westPanel.add(new JLabel("Threes",JLabel.CENTER));
    westPanel.add(new JLabel("Count and Add Only Threes",JLabel.CENTER));
    westPanel.add(westLabels[3]);
    westPanel.add(westButtons[3]);
    westPanel.add(new JLabel("Fours",JLabel.CENTER));
    westPanel.add(new JLabel("Count and Add Only Fours",JLabel.CENTER));
    westPanel.add(westLabels[4]);
    westPanel.add(westButtons[4]);
    westPanel.add(new JLabel("Fives",JLabel.CENTER));
    westPanel.add(new JLabel("Count and Add Only Fives",JLabel.CENTER));
    westPanel.add(westLabels[5]);
    westPanel.add(westButtons[5]);
    westPanel.add(new JLabel("Sixes",JLabel.CENTER));
    westPanel.add(new JLabel("Count and Add Only Sixes",JLabel.CENTER));
    westPanel.add(westLabels[6]);
    westPanel.add(westButtons[6]);
    westPanel.add(new JLabel("",JLabel.CENTER));
    westPanel.add(new JLabel("",JLabel.CENTER));
    westPanel.add(westLabels[7]);
    westPanel.add(westButtons[7]);
    westLabels[7].setVisible(false);
    westButtons[7].setVisible(false);
    westPanel.add(new JLabel("",JLabel.CENTER));
    westPanel.add(new JLabel("",JLabel.CENTER));
    westPanel.add(westLabels[8]);
    westPanel.add(westButtons[8]);
    westButtons[8].setText("End round");
    westLabels[8].setVisible(false);
    westPanel.add(new JLabel("Bonus",JLabel.CENTER));
    westPanel.add(new JLabel("If Total Score is over 63, add 35",JLabel.CENTER));
    westPanel.add(westLabels[9]);
    westPanel.add(westButtons[9]);
    westButtons[9].setVisible(false);
    
    westButtons[1].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
          int scoreToAdd = 0;
          for(int i =0; i < dice.length ; i++){
            if(dice[i].getResult() == 1){
              scoreToAdd+=1;
            }
          }
          westLabels[1].setText(String.valueOf(scoreToAdd) + " Points");
          westButtons[1].setVisible(false);
          bonusScore+=scoreToAdd;
          totalScore += scoreToAdd;
          totalScoreLabel.setText(String.valueOf(totalScore));
          scoreSelected();
      }
    });
    
    westButtons[2].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
          int scoreToAdd = 0;
          for(int i =0; i < dice.length ; i++){
            if(dice[i].getResult() == 2){
              scoreToAdd+=2;
            }
          }
          westLabels[2].setText(String.valueOf(scoreToAdd) + " Points");
          westButtons[2].setVisible(false);
          totalScore += scoreToAdd;
          totalScoreLabel.setText(String.valueOf(totalScore));
          bonusScore+=scoreToAdd;
          scoreSelected();
      }
    });
    
    westButtons[3].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
          int scoreToAdd = 0;
          for(int i =0; i < dice.length ; i++){
            if(dice[i].getResult() == 3){
              scoreToAdd+=3;
            }
          }
          westLabels[3].setText(String.valueOf(scoreToAdd) + " Points");
          westButtons[3].setVisible(false);
          totalScore += scoreToAdd;
          totalScoreLabel.setText(String.valueOf(totalScore));
          bonusScore+=scoreToAdd;
          scoreSelected();
      }
    });
    
    westButtons[4].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
          int scoreToAdd = 0;
          for(int i =0; i < dice.length ; i++){
            if(dice[i].getResult() == 4){
              scoreToAdd+=4;
            }
          }
          westLabels[4].setText(String.valueOf(scoreToAdd) + " Points");
          westButtons[4].setVisible(false);
          totalScore += scoreToAdd;
          totalScoreLabel.setText(String.valueOf(totalScore));
          bonusScore+=scoreToAdd;
          scoreSelected();
      }
    });
    
    westButtons[5].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
          int scoreToAdd = 0;
          for(int i =0; i < dice.length ; i++){
            if(dice[i].getResult() == 5){
              scoreToAdd+=5;
            }
          }
          westLabels[5].setText(String.valueOf(scoreToAdd) + " Points");
          westButtons[5].setVisible(false);
          totalScore += scoreToAdd;
          totalScoreLabel.setText(String.valueOf(totalScore));
          bonusScore+=scoreToAdd;
          scoreSelected();
      }
    });
    
    westButtons[6].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
          int scoreToAdd = 0;
          for(int i =0; i < dice.length ; i++){
            if(dice[i].getResult() == 6){
              scoreToAdd+=6;
            }
          }
          westLabels[6].setText(String.valueOf(scoreToAdd) + " Points");
          westButtons[6].setVisible(false);
          totalScore += scoreToAdd;
          totalScoreLabel.setText(String.valueOf(totalScore));
          bonusScore+=scoreToAdd;
          scoreSelected();
      }
    });
    
    westButtons[8].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
          scoreSelected();
      }
    });
    
    // End West Panel
    
    // Center Panel
    for (int i = 0 ; i < dice.length ; i++){
      dice[i] = new Die(6);
    }
    for (int i = 0 ; i < 27 ; i++){
      centerPanel.add(new JLabel(""));
    }
    
    centerPanel.add(new JLabel("Dice",JLabel.CENTER));
    centerPanel.add(new JLabel(""));
    centerPanel.add(new JLabel(""));
    for (int i = 0 ; i < diceLabels.length ; i++){
      diceLabels[i] = new JLabel();
      String temp = String.valueOf(dice[i].getResult());
      diceLabels[i].setHorizontalAlignment(JLabel.CENTER);
      diceLabels[i].setVerticalAlignment(JLabel.CENTER);
      diceLabels[i].setText(temp);
      centerPanel.add(diceLabels[i]);
      diceLabels[i].setBorder (BorderFactory.createLineBorder(Color.black));
    }
    for (int i = 0 ; i < diceSelectButtons.length ; i++){
      diceSelectButtons[i] = new JButton("Select");
      diceSelectButtons[i].setBackground(Color.GREEN);
      diceSelectButtons[i].setVisible(false);
      centerPanel.add(diceSelectButtons[i]);
    }
    centerPanel.add(new JLabel(""));
    centerPanel.add(new JLabel(""));
    centerPanel.add(rollButton);
    for (int i = 0 ; i < 14 ; i++){
      centerPanel.add(new JLabel(""));
    }
    JButton newGame = new JButton("New Game");
    centerPanel.add(newGame);
    
    //newGame ActionListener
    newGame.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
          startNewGame();
      }
    });
    
    //RollButton ActionListener
    rollButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
          for(int i =0; i < dice.length ; i++){
            if(diceSelectButtons[i].getBackground() == Color.GREEN){
              diceLabels[i].setText(String.valueOf(dice[i].roll()));
              diceSelectButtons[i].setVisible(true);
            }
          }
          rollNumLabel.setText("Roll " + String.valueOf(++roll) + "/3");
          if (roll == 3){
            rollButton.setEnabled(false);
          }
          for (int i = 0 ; i < eastButtons.length ; i++){
            eastButtons[i].setEnabled(false);
            westButtons[i].setEnabled(false);
          }
          westButtons[8].setVisible(true);
          westButtons[8].setEnabled(true);
          checkScore();
      }
    });
    //diceSelectButtons ActionListeners
    diceSelectButtons[0].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
          if (diceSelectButtons[0].getBackground() == Color.RED){
            diceSelectButtons[0].setBackground(Color.GREEN);
          }
          else if(diceSelectButtons[0].getBackground() == Color.GREEN){
            diceSelectButtons[0].setBackground(Color.RED);
          }
      }
    });
    diceSelectButtons[1].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
          if (diceSelectButtons[1].getBackground() == Color.RED){
            diceSelectButtons[1].setBackground(Color.GREEN);
          }
          else if(diceSelectButtons[1].getBackground() == Color.GREEN){
            diceSelectButtons[1].setBackground(Color.RED);
          }          
      }
    });
    diceSelectButtons[2].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
          if (diceSelectButtons[2].getBackground() == Color.RED){
            diceSelectButtons[2].setBackground(Color.GREEN);
          }
          else if(diceSelectButtons[2].getBackground() == Color.GREEN){
            diceSelectButtons[2].setBackground(Color.RED);
          }          
      }
    });
    diceSelectButtons[3].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
          if (diceSelectButtons[3].getBackground() == Color.RED){
            diceSelectButtons[3].setBackground(Color.GREEN);
          }
          else if(diceSelectButtons[3].getBackground() == Color.GREEN){
            diceSelectButtons[3].setBackground(Color.RED);
          }          
      }
    });
    diceSelectButtons[4].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
          if (diceSelectButtons[4].getBackground() == Color.RED){
            diceSelectButtons[4].setBackground(Color.GREEN);
          }
          else if(diceSelectButtons[4].getBackground() == Color.GREEN){
            diceSelectButtons[4].setBackground(Color.RED);
          }
          
      }
    });
    // End Center Panel
    
    
    mainPanel.add(northPanel, BorderLayout.NORTH);
    mainPanel.add(eastPanel, BorderLayout.EAST);
    mainPanel.add(centerPanel, BorderLayout.CENTER);
    mainPanel.add(westPanel, BorderLayout.WEST);
    mainPanel.add(southPanel, BorderLayout.SOUTH);
    
    this.add(mainPanel);
    this.setVisible(true);
  }
  //end constructor
  
  //when westbuttons/eastbuttons is pressed
  private void scoreSelected(){
    roll= 0;
    rollNumLabel.setText("Roll " + String.valueOf(roll) + "/3");
    roundLabel.setText("Round " + String.valueOf(++round) + "/13");
    for (int i = 0 ; i < eastButtons.length ; i++){
      eastButtons[i].setEnabled(false);
      westButtons[i].setEnabled(false);
    }
    for (int i = 0 ; i < diceSelectButtons.length ; i++){
      diceSelectButtons[i].setBackground(Color.GREEN);
      diceSelectButtons[i].setVisible(false);
      diceLabels[i].setText("-1");
    }
    if (westLabels[9].getText() == "0 Points" && bonusScore >= 63){
      westLabels[9].setText("35 Points");
      totalScore += 35;
      totalScoreLabel.setText(String.valueOf(totalScore));
    }
    if (round <= 13){
    rollButton.setEnabled(true);
    }
    else{
      for (int i = 0 ; i < diceLabels.length ; i++){
        diceLabels[i].setText("Game Over");
      }
    }
  }
  //end scoreSelected()
  
  //begin checkScore()
  private void checkScore(){
    
    //check if dice can score for west buttons
      //aces
    if (westButtons[1].isVisible() == true){
      for (int i = 0 ; i < dice.length; i++){
        if (dice[i].getResult() == 1){
          westButtons[1].setEnabled(true);
        }
      }
    }
      //twos
    if (westButtons[2].isVisible() == true){
      for (int i = 0 ; i < dice.length; i++){
        if (dice[i].getResult() == 2){
          westButtons[2].setEnabled(true);
        }
      }
    }
      //threes
    if (westButtons[3].isVisible() == true){
      for (int i = 0 ; i < dice.length; i++){
        if (dice[i].getResult() == 3){
          westButtons[3].setEnabled(true);
        }
      }
    }
      //fours
    if (westButtons[4].isVisible() == true){
      for (int i = 0 ; i < dice.length; i++){
        if (dice[i].getResult() == 4){
          westButtons[4].setEnabled(true);
        }
      }
    }
      //fives
    if (westButtons[5].isVisible() == true){
      for (int i = 0 ; i < dice.length; i++){
        if (dice[i].getResult() == 5){
          westButtons[5].setEnabled(true);
        }
      }
    }
      //sixes
    if (westButtons[6].isVisible() == true){
      for (int i = 0 ; i < dice.length; i++){
        if (dice[i].getResult() == 6){
          westButtons[6].setEnabled(true);
        }
      }
    }
    //end check on west buttons
    //begin check for east Buttons
      //3 of a kind
    if (eastButtons[1].isVisible() == true){
      int temp = 1;
      for(int i = 0 ; i < 3; i++){
        for(int j = i+1 ; j < dice.length; j++){
          if (dice[i].getResult() == dice[j].getResult()){
            temp++;
          }
        }
        if (temp >=3){
          eastButtons[1].setEnabled(true);
        }
        temp = 1;
      }
    }
      //4 of a kind
    if (eastButtons[2].isVisible() == true){
      int temp = 1;
      for(int i = 0 ; i < 2; i++){
        for(int j = i+1 ; j < dice.length; j++){
          if (dice[i].getResult() == dice[j].getResult()){
            temp++;
          }
        }
        if (temp >=4){
          eastButtons[2].setEnabled(true);
        }
        temp = 1;
      }
    }
      //full house
    if (eastButtons[3].isVisible() == true){
      int[] temp = new int[5];
      for (int i = 0 ; i < dice.length ; i++){
        temp[i] = dice[i].getResult();
      }
      Arrays.sort(temp);
      if (temp[0] == temp[1]&&
          temp[1] == temp[2]&&
          temp[3] == temp[4]){
        eastButtons[3].setEnabled(true);
      }
      if (temp[0] == temp[1]&&
          temp[2] == temp[3]&&
          temp[3] == temp[4]){
        eastButtons[3].setEnabled(true);
      }
    }
      //sm straight
    if (eastButtons[4].isVisible() == true){
      int[] arr = new int[5];
      for (int i = 0 ; i < dice.length ; i++){
        arr[i] = dice[i].getResult();
      }
      Arrays.sort(arr);
      
      int[] temp = new int[arr.length]; 
      
      // Code to remove duplicates from our dice
      // Start traversing elements 
      int j = 0; 
      for (int i=0; i<arr.length-1; i++){ 
          // If current element is not equal 
          // to next element then store that 
          // current element 
        if (arr[i] != arr[i+1]){ 
          temp[j++] = arr[i]; 
        }
      }
      // Store the last element as whether 
      // it is unique or repeated, it hasn't 
      // stored previously 
      temp[j++] = arr[arr.length-1];    
         
      // Modify original array 
      for (int i=0; i<j; i++){ 
          arr[i] = temp[i]; 
      }
      if ((temp[0] == temp[1]-1 &&
           temp[1] == temp[2]-1 &&
           temp[2] == temp[3]-1)||
          (temp[3] == temp[4]-1 &&
           temp[1] == temp[2]-1 &&
           temp[2] == temp[3]-1)){
        eastButtons[4].setEnabled(true);
      }
    }
      // lg straight
    if (eastButtons[5].isVisible() == true){
      int[] temp = new int[5];
      for (int i = 0 ; i < dice.length ; i++){
        temp[i] = dice[i].getResult();
      }
      Arrays.sort(temp);
     if (temp[0] == temp[1] -1 &&
         temp[1] == temp[2] -1 &&
         temp[2] == temp[3] -1 &&
         temp[3] == temp[4] -1){
        eastButtons[5].setEnabled(true);
      }
    }
      // yahtzee
    Boolean same = true;
    for(int i = 1; i < dice.length ; i++){
      if(dice[0].getResult() != dice[i].getResult()){
        same =false;
        break;
      }
    }
    if (same){
      eastButtons[6].setEnabled(true);
    }
      // chance
    if (eastButtons[7].isVisible() == true){
      eastButtons[7].setEnabled(true);
    }
        
  }
  //end checkScore()
  
  //begin startNewGame()
  private void startNewGame(){
    totalScore = 0;
    roll = 0;
    round = 1;
    bonusScore = 0;
    roundLabel.setText("Round " + String.valueOf(round) + "/13");
    rollNumLabel.setText("Roll " + String.valueOf(roll) + "/3");    
    totalScoreLabel.setText(String.valueOf(totalScore));
    for (int i = 0 ; i < eastLabels.length ; i++){
      eastLabels[i].setText("0 Points");
    }
    for (int i = 0 ; i < westLabels.length ; i++){
      westLabels[i].setText("0 Points");
    }
    for (int i = 0 ; i < eastButtons.length; i++){
      eastButtons[i].setVisible(true);
      eastButtons[i].setEnabled(false);
    }
    eastButtons[0].setVisible(false);
    eastButtons[8].setVisible(false);
    eastButtons[9].setVisible(false);
    for (int i = 0 ; i < westButtons.length; i++){
      westButtons[i].setVisible(true);
      westButtons[i].setEnabled(false);
    }
    westButtons[7].setVisible(false);
    westButtons[9].setVisible(false);
    for (int i = 0 ; i < dice.length ; i++){
      dice[i] = new Die(6);
    }
    for (int i = 0 ; i < diceSelectButtons.length ; i++){
      diceSelectButtons[i].setBackground(Color.GREEN);
      diceSelectButtons[i].setVisible(false);
    }
    for (int i = 0 ; i < diceLabels.length ; i++){
      diceLabels[i].setText(String.valueOf(dice[i].getResult()));
    }
    rollButton.setEnabled(true);
  }
  // end startNewGame()
  
  public static void main(String[] args){
    new Yahtzee();
  }
}