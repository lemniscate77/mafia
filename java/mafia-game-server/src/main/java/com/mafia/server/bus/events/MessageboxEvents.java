/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mafia.server.bus.events;

import com.mafia.server.io.MessageRouter;
import com.mafia.server.model.comm.server.Messagebox;
import com.mafia.server.model.state.Game;
import com.mafia.server.model.state.Player;

/**
 *
 * @author Just1689
 */
public class MessageboxEvents {

    public static void showMessageboxOk(Game game, String message) {
        Messagebox messagebox = Messagebox.createMessageBoxOk(message);
        MessageRouter.sendMessage(game, messagebox);
    }

    public static void showMessageboxTimed(Game game, String title, String message) {
        Messagebox messagebox = Messagebox.createMessageBoxTimed(title, message);
        MessageRouter.sendMessage(game, messagebox);
    }

    public static void notifyOfFail(String title, String message, String sessionId) {
        Player player = new Player("", "", sessionId);
        notifyOfFail(title, message, player);

    }

    public static void notifyOfFail(String title, String message, Player player) {
        Messagebox messagebox = Messagebox.createMessageBoxError(title, message);
        MessageRouter.sendMessage(player, messagebox);

    }

}
