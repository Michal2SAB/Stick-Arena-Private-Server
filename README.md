# Stick-Arena-Private-Server
Improved version of BallistickEMU. The original is probably entirely written by Simon but I'm not sure.

# What changed?

- Added CredTicketHandler. You can now collect cred ticket when it's available.
- Added DoubleLoginHandler. You can't have two of the same accounts in the same server at the same time anymore, you will be notified that somebody just logged in to your account.
- Better ban handling. Ban packet actually being sent to the client (user getting banned) with a time and message, instead of getting disconnected.
- Fixed lobby appearance bug. Sometimes users were invisible after joining lobby and.. it was a mess.
- Fixed game bug - reset player's kills and deaths if they rejoin the room.
- Wrote win/loss logic, same as on original SA server. Not the best code, change it if you can cus I don't care.
- Partially fixed the inventory glitch. You can now select the spinner you purchased/added without logging out first, but when you log out you will have your first spinner selected.
- Added !bluehead command. Gives you the original bluehead color and the spinner as on original stick arena. Requires 588 version of SA SWF client.
- !fuzzyspinner, !builderspinner, !candycane, !heartsspinner are not simply just !fuzzy, !builder, !cane and !hearts.
- Easier to read syntax help for color and spinner commands (no annoying black glitch with the <> characters)
- Fixed stick_arena.php to work with PHP 7 and up.

# Hints for those who want to continue improving the project

## Fix the inventory

Buying or adding a new item to database will not properly add it to the stick inventory data you've already read. You can't properly set it as selected, and if you buy another item, it will overwrite the previous one (on client only, it will exist on database) 
<details>
   <summary>Possible solutions</summary>
  
   1. Make the player's client automatically re-read and re-set inventory data from the inventory table after purchasing or adding an item.
   2. Make the player automatically re-login (bad idea, noob idea).
   3. Change the way inventories are being handled entirely. Rewrite the code.
</details>

## Fix the in-game kick

Kicking a player is not working for regular players. It works for moderators but it just disconnects a player, and that's stupid.
<details>
   <summary>Solutions</summary>
  
   1. In KickHandler, make everybody's kick vote for a new user add to a list.
   2. Have a listener or something like that for that list, to keep checking if that list finally contains x amount of votes.
   3. Also keep checking for how many players in total there are in the game.
   4. Have a var x to keep updating. If the game has 3 players, 2 kicks are required. If 4, then 3 are required and so on. Like on sa.
   5. Once the list reached x amount of votes, send the "K" packet to the voted user.
   6. Add the kicked out user's id to a blacklist so he can't join this game again.
</details>

## Properly deregister rooms if nobody is in them

Although games disappear from the 01 room request packet for everybody's client, they are still active or remembered in the server so creating another room with the same name will make you litteraly disappear until you come back to lobby.
<details>
   <summary>Possible solutions</summary>
   
   1. Check if there is any StickRoom type list or whatever where all new rooms are additionally kept and make sure the room is removed from there aswel. It may be located both in StickRoom and StickClient tbh.
   2. Follow anything related to StickRoom or StickRoomRegistry line by line and find the issue. I honestly didn't even get to this so idk myself, just assuming.
</details>

## !setkills is buggy

I think I recall setkills either not working or just being buggy.. look into it.
<details>
   <summary>Solutions</summary>
  
   - There's probably a small error, find it and get rid of it in PlayersCommandsHandler.
   - Probably will have to re-read and re-set the player's stats from database again just like with the inventory glitch or re-login.
</details>

## Remove ::disconnect command

Do I need to say more? Remove that cancerous command, unless you want to moderate only by yourself. We know how mods on the original SA were, you don't want that all over again. Or just separate mod powers with user level 1 and user level 2.

## Improve ::ban further

Ok. So I made the player receive ban message but what about giving the user actual ban reason and time? It's not neccessary but we get closer to real SA if you add it. Would require for you to add a ban table on database to keep the reasons and create ban schedule/event.

## Create event on database for cred ticket.

You can make it available per every login but that can be easily abused for no reason. Create an event on database instead to keep setting tickets to 1 for users every 24 hours. 

## Have a table for custom maps

Ok, the xgen api is still up.. but what if it's not? And what if you're using an account name that you don't actually have the password to on original SA? You're stuck.

<details>
   <summary>Solutions</summary>
   
   1. Create new table on database for custom maps of users.
   2. Write custom api for saving and loading maps from your own database.
   3. Replace the api links in SA SWF client.
   
</details>

# Why won't you finish the improvement?

I'm moving on. I realized that if I host this server for the european players, I'll just be stuck with stick arena and the community for years again. It'stime to move on. 

Good luck =)
