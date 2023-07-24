package edu.lewisu.cs.example.quizapp;

class Question {
   String questionText;
   boolean answerTrue;

   public Question(String questionText, boolean answerTrue) {
       this.questionText = questionText;
       this.answerTrue = answerTrue;
   }

   public String getQuestionText() {
       return questionText;
   }

   public void setQuestionText(String questionText) {
       this.questionText = questionText;
   }

   public boolean isAnswerTrue() {
       return answerTrue;
   }

   public void setAnswerTrue(boolean answerTrue) {
       this.answerTrue = answerTrue;
   }
}
