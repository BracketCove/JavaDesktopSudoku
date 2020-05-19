package com.wissassblog.sudoku.userinterface;

import com.wissassblog.sudoku.problemdomain.SudokuGame;

/**
 * Contract is really just another word for interface, which is another word for a Protocol.
 * All of these words mean: "something (a file in this case) which describes how one or more objects
 * may interact with each other without having to know too much about each other."
 *
 * For example, let's say you order food to be delivered to your house. You expect the delivery person to show
 * (hopefully soon) to accept your payment and give you food. Both you and the driver are aware that this is how
 * you can interact with each other successfully. The driver must give you food, and you must pay the driver.
 *
 * If I was to describe that Contract/Interface/Protocol/Abstraction (English unfortunately has many words for one
 * thing) in Java code, it might look like:
 *
 *  interface FoodService {
 *      interface Customer {
 *         void acceptFood(Food food);
 *      }
 *
 *      interface DeliveryPerson {
 *         void acceptPayment(Money money);
 *      }
 *  }
 *
 *
 */
public interface IUserInterfaceContract {

    //Short is just a smaller version of an "int". Although computers have become very powerful,
    //it is still good practice to use the smallest possible data structure, unless legibility (such as an enum)
    //is a more important concern for the problem in front of you.
    interface EventListener {
        void onSudokuInput(int x, int y, int input);
        void onDialogClick();
    }


    //View refers to what the user can "View", or "See". In English, the word is both a noun and a verb.
    interface View {
        void setListener(IUserInterfaceContract.EventListener listener);
        //update a single square after user input
        void updateSquare(int x, int y, int input);

        //update the entire board, such as after game completion or initial execution of the program
        void updateBoard(SudokuGame game);
        void showDialog(String message);
        void showError(String message);
    }
}