# Lan World Discord Integration
It is a mod that allows the user to link their singleplayer/lan world or a multiplayer server to a channel in a discord server, **only the host should have this mod installed**.
## Installation Guide
### Installing the mod
First, drag the mod file into your mod folder. Fabric api is **required** for all versions. Use fabric version **v0.16.14** or above.

If you are using forge, install Sinytra Connector and Forgified fabric api.

<img width="630" height="494" alt="image" src="https://github.com/user-attachments/assets/b925d3a4-2330-448a-b4c7-1f0d0182f62c" />

Now run the game, a file with the name `lan-world-discord-integration.toml` should be generated in your config folder: 
<img width="796" height="71" alt="image" src="https://github.com/user-attachments/assets/95b9617b-92cc-491a-82e2-37a9b43143aa" />

After this step, you can close Minecraft and start setting up the discord bot.


### Discord Bot setup
1. In [discord developer portal](https://discord.com/developers/applications), click new applications.
<img width="1897" height="901" alt="image" src="https://github.com/user-attachments/assets/e8dfe185-4cdb-4e80-b712-f95c38cfe3cf" />

Then  check the following permissions:
<img width="1911" height="828" alt="image" src="https://github.com/user-attachments/assets/4a4d13b3-551e-41f5-8f5d-4f49bd74b517" />
<img width="1918" height="887" alt="image" src="https://github.com/user-attachments/assets/f35d6131-c850-46fa-a6c0-6c50967ab24e" />
<img width="1660" height="808" alt="image" src="https://github.com/user-attachments/assets/3336eb80-ed9e-47cd-8152-96efd17eb609" />

Now copy the link below and paste it in a new tab.
<img width="1554" height="219" alt="image" src="https://github.com/user-attachments/assets/7b288148-cee9-4f40-b6b6-17962853b4a5" />
<img width="643" height="809" alt="image" src="https://github.com/user-attachments/assets/43b7853a-71ec-464e-aeef-17d4ef0e56aa" />

If you can't add the bot to your discord server, it could be because you don't have the `Manage Server` permission in your discord server.
After your bot is in your server, time to set it up in Minecraft.

### Configuring the config file
Go to the bot page in developer portal and click `Reset Token`: 

<img width="1292" height="434" alt="image" src="https://github.com/user-attachments/assets/3d2b6582-c27f-4d2c-9acb-a1ab24cbb3a6" />

Then copy it.

<img width="849" height="158" alt="image" src="https://github.com/user-attachments/assets/561eb882-8253-48e2-8677-3247cd323cb5" />

Open `lan-world-discord-integration.toml`, paste the token in like this, make sure you save the file too. 

<img width="1105" height="127" alt="image" src="https://github.com/user-attachments/assets/d5c488f3-e19e-45c3-bace-c4810031e1fb" />

Now go to your discord settings, in `Advanced` section, enable developer mode.

<img width="1568" height="1014" alt="image" src="https://github.com/user-attachments/assets/e7b56e1b-6160-414f-a206-5c10f4e92a6e" />

Go to your server and right click the channel you want the bot to be at, then click `Copy Channel Id`

<img width="440" height="821" alt="image" src="https://github.com/user-attachments/assets/0574b4f5-c6ad-4ed9-9797-8639e3164321" />

Now paste the channel id in like this in `lan-world-discord-integration.toml`, make sure to save the file.

<img width="1129" height="142" alt="image" src="https://github.com/user-attachments/assets/7a852e9b-2032-49f0-826e-d8fded6d9948" />

Now you can start Minecraft, once you enter a world, the bot should start automatically. I will add a command in game to allow the user to start and stop the bot in the future I promise.

This is what it should look like once it started running normally:

<img width="352" height="147" alt="image" src="https://github.com/user-attachments/assets/aa1fc8ff-c761-4b73-88eb-d8db65d71b2f" />

<img width="1328" height="146" alt="image" src="https://github.com/user-attachments/assets/cd83bcbd-95c5-47db-912e-0bc516da7a46" />

Now enjoy your simple Minecraft-Discord messenger!

-BackslashTrash, 2025-07-25







