package application;




import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import entities.Account; 
import entities.User;
import sessionManagement.SessionManagement;
import sessionManagement.SessionManagementTools;
import utils.AccountTools;
import utils.AdminAccountMenus;
import utils.AdminMenus;
import utils.AdminReclamationMenu;
import utils.Menus;
import utils.Tools;
import utils.UserMenus;
import utils.UserTools;

public class Application {
	
	public static String header=""

			+ "  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\r\n"
			+ "  |                                                    ______                                                             _                                                               |\r\n"
			+ "  |           /\\                               _      |  ___ \\                                                 _         | |              _                                               |\r\n"
			+ "  |          /  \\   ____ ____ ___  _   _ ____ | |_    | | _ | | ____ ____   ____  ____  ____ ____   ____ ____ | |_        \\ \\  _   _  ___| |_  ____ ____                                  |\r\n"
			+ "  |         / /\\ \\ / ___) ___) _ \\| | | |  _ \\|  _)   | || || |/ _  |  _ \\ / _  |/ _  |/ _  )    \\ / _  )  _ \\|  _)        \\ \\| | | |/___)  _)/ _  )    \\                                 |\r\n"
			+ "  |        | |__| ( (__( (__| |_| | |_| | | | | |__   | || || ( ( | | | | ( ( | ( ( | ( (/ /| | | ( (/ /| | | | |__    _____) ) |_| |___ | |_( (/ /| | | |                                |\r\n"
			+ "  |        |______|\\____)____)___/ \\____|_| |_|\\___)  |_||_||_|\\_||_|_| |_|\\_||_|\\_|| |\\____)_|_|_|\\____)_| |_|\\___)  (______/ \\__  (___/ \\___)____)_|_|_|                                |\r\n"
			+ "  |                                                                             (_____|                                       (____/                                                      |";
	public static void main(String[] args) throws Exception {
		
		Tools.CreateFileIfNoteExist();
		
		boolean gameOver = false;
		while ( !gameOver) {
			int c = Menus.HomeMenu();
			if(c==0) {
				gameOver = true;
			}else if(c==1) {
				int j = Menus.LoginMenu();
				
				if(j==1) {
					//login success and he is an admin
					boolean adminGameOver= false;
					while(!adminGameOver) {
						
						int r = AdminMenus.AdminMenu();
						
						if(r==0) {
							adminGameOver=true;
						}else if(r==1) {
							
							boolean listOfUsersGameOver = false;
							while(!listOfUsersGameOver) {
								int a = AdminMenus.ListOfUsers();
								
								if(a==1) {
									int b = AdminMenus.addNewUser(); 
								}else if(a==2) {
										
								}else if(a==3) {
									AdminMenus.deleteUser();
								}else if(a==4) {
									//user details
								}else if(a==5) {
									//return
								}else if(a==0) {
									listOfUsersGameOver= true;
								}
								
							}
							
						}else if(r==2) {
							boolean listOfAccountCheck = false;
							while(!listOfAccountCheck) {
								int a = AdminAccountMenus.ListOfAccount();
								switch(a) {
									case 0 :{listOfAccountCheck=true;break;}
									case 1 :{AdminAccountMenus.activateAccountMenu();break;}
									case 2 :{AdminAccountMenus.desactivateAccountMenu();break;}
									default : break;
								}
							}
						}else if(r==3) {
							boolean profileGameOver = false;
							while(!profileGameOver) {
								int ss = AdminMenus.AdminProfile(SessionManagementTools.getSession().getSessionUser());
								switch(ss) {
								 case 0 :{profileGameOver = true;break;}
								 case 1 :{
									 UserMenus.editProfile(SessionManagementTools.getSession().getSessionUser());break;
									 }
								 default:break;
								}
							} 
						}else if(r==4) {
							// Admin accounts
							boolean adminAccountGameOver = false;
							while(!adminAccountGameOver) {
								int adminAcc = UserMenus.userAccounts(SessionManagementTools.getSession().getSessionUser());
								switch(adminAcc) {
								case 0:{adminAccountGameOver = true;break;}
								case 1 : {
									 Menus.withDrawMoneyMenu(SessionManagementTools.getSession().getSessionUser());
									break;}
								case 2 : {
									 Menus.depositMoneyMenu(SessionManagementTools.getSession().getSessionUser());
									break;
								}
								default : break;
							}
							}
						}else if(r==5) {
								AdminMenus.statistiquesMenu();
						}else if(r==6) {
								//history
							Menus.myHistory(SessionManagementTools.getSession().getSessionUser());
						}else if(r==7) {
							Menus.myAccountHistory(SessionManagementTools.getSession().getSessionUser().getId());
						}else if(r==8) {
							boolean reclamationGameOver = false;
							while(!reclamationGameOver) {
								int rec=AdminReclamationMenu.listOfReclamationMenu();
								switch(rec) {
								case 1 : {
									AdminReclamationMenu.answerReclamation();
									break;
								}
								case 0 : {
									reclamationGameOver = true;
									break;
								}
								default : {reclamationGameOver= true;break;}
								}
							}
						}
						
					}
					
				}else if(j==0) {
					//login failed
				}else if(j==11) {
					boolean userMenuGameOver = false;
					while(!userMenuGameOver) {
						int userMenu = UserMenus.userMenu();
						switch(userMenu) {
							case 0 : {userMenuGameOver = true;break;}
							case 1 : {
								boolean userProfileGameOver = false;
								while(!userProfileGameOver) {
									
									int us = UserMenus.userProfile(SessionManagementTools.getSession().getSessionUser());
									switch(us) {
										case 0 :{userProfileGameOver=true;break;}
										case 1 : { UserMenus.editProfile(SessionManagementTools.getSession().getSessionUser());break;}
										default:break;
									}
								}
								break;
							}
							case 2 :{
								boolean userAccountGameOver = false;
								while(!userAccountGameOver) {
									int usa=UserMenus.userAccounts(SessionManagementTools.getSession().getSessionUser());
									switch(usa) {
										case 0 : {userAccountGameOver = true;break;}
										case 1 : {
												Menus.withDrawMoneyMenu(SessionManagementTools.getSession().getSessionUser());
												break;
										}
										case 2:{
											 Menus.depositMoneyMenu(SessionManagementTools.getSession().getSessionUser());
											break;
										}
									}
								}
								break;
							}
							case 3:  {
									Menus.myHistory(SessionManagementTools.getSession().getSessionUser());
								break;}
							case 4 : {
									Menus.withDrawMoneyMenu(SessionManagementTools.getSession().getSessionUser());
									break;
							}
							case 5:{
								Menus.depositMoneyMenu(SessionManagementTools.getSession().getSessionUser());
								break;
							}
							case 6:{
								Menus.myAccountHistory(SessionManagementTools.getSession().getSessionUser().getId());
								break;
							}
							case 7 : {
								UserMenus.writeReclamationMenu(SessionManagementTools.getSession().getSessionUser());
								break;
							}
							case 8 :{
								UserMenus.listOfUserReclamationMenu(SessionManagementTools.getSession().getSessionUser());
								
								break;
							}
								
							default  :{break;}
						}
					}
				}
				
			}else if(c==2) {
				 Menus.singUpUser();
			}else if(c==3) {
				Menus.aboutUsMenu();
			}else if(c==4500) {
				AdminMenus.addNewAdmin();
				//create new Admin 
			}
		}
		

	}
	
	

}


