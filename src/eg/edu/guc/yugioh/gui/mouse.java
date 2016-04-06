package eg.edu.guc.yugioh.gui;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class mouse implements MouseMotionListener {

	//Controller control;
	JButton hover;

	public mouse() {
		//control = Main.getC();
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent event) {
		// TODO Auto-generated method stub
		String s = "";
		if (hover == null) {
			if (event.getSource() instanceof MonsterButton) {
				hover = (MonsterButton) event.getSource();
				s = hover.getText();
			}
			if(event.getSource() instanceof SpellButton)
			{
				hover = (SpellButton )event.getSource();
				s = hover.getText();
			}
			if(event.getSource() instanceof HandButtons)
			{
				hover = (HandButtons) event.getSource();
				s = hover.getText();
			}
				
				switch (s) {
				case ("Blue-Eyes White Dragon"):
					JFrame a = new JFrame();
					ImageIcon ImageIcon = new ImageIcon(
							s+".jpg");
					Image Image = ImageIcon.getImage();
					a.setIconImage(Image);
					break;
				case ("Cosmo Queen"):
					JFrame b = new JFrame();
					ImageIcon ImageIcon1 = new ImageIcon(
							s+".jpg");
					Image Image1 = ImageIcon1.getImage();
					b.setIconImage(Image1);
					break;
				case ("Dark Magician"):
					JFrame c = new JFrame();
				ImageIcon ImageIcon2 = new ImageIcon(
						s+".jpg");
				Image Image2 = ImageIcon2.getImage();
				c.setIconImage(Image2);
				break;
					
				case ("Fence Guard"):
					JFrame d = new JFrame();
				ImageIcon ImageIcon3 = new ImageIcon(
						s+".jpg");
				Image Image3 = ImageIcon3.getImage();
			d.setIconImage(Image3);
				break;
				case ("Red-Eyes Black Dragon"):
					JFrame e = new JFrame();
				ImageIcon ImageIcon4 = new ImageIcon(
						s+".jpg");
				Image Image4 = ImageIcon4.getImage();
				e.setIconImage(Image4);
				break;
					
				case ("Gaia The Fierce Knight"):
					JFrame f = new JFrame();
				ImageIcon ImageIcon5 = new ImageIcon(
						s+".jpg");
				Image Image5 = ImageIcon5.getImage();
				f.setIconImage(Image5);
				break;
				case ("Summoned Skull"):
					JFrame g = new JFrame();
				ImageIcon ImageIcon6 = new ImageIcon(
						s+".jpg");
				Image Image6 = ImageIcon6.getImage();
				g.setIconImage(Image6);
				break;
				case ("Fence Guard Magician"):
					JFrame h = new JFrame();
				ImageIcon ImageIcon7 = new ImageIcon(
						s+".jpg");
				Image Image7 = ImageIcon7.getImage();
				h.setIconImage(Image7);
				break;
				case ("Dark Magician Girl"):
					JFrame i = new JFrame();
				ImageIcon ImageIcon8 = new ImageIcon(
						s+".jpg");
				Image Image8 = ImageIcon8.getImage();
				i.setIconImage(Image8);
				break;
				case ("Curse Of Dragon"):
					JFrame j = new JFrame();
				ImageIcon ImageIcon9 = new ImageIcon(
						s+".jpg");
				Image Image9 = ImageIcon9.getImage();
				j.setIconImage(Image9);
				break;
				case ("Fence Guard Dragon"):
					JFrame k = new JFrame();
				ImageIcon ImageIcon10 = new ImageIcon(
						s+".jpg");
				Image Image10 = ImageIcon10.getImage();
				k.setIconImage(Image10);
				break;
				case ("Alexandrite Dragon"):
					JFrame l = new JFrame();
				ImageIcon ImageIcon11 = new ImageIcon(
						s+".jpg");
				Image Image11 = ImageIcon11.getImage();
				l.setIconImage(Image11);
				break;
				case ("Vorse Raider"):
					JFrame m = new JFrame();
				ImageIcon ImageIcon12 = new ImageIcon(
						s+".jpg");
				Image Image12 = ImageIcon12.getImage();
				m.setIconImage(Image12);
				break;
				case ("Gemini Elf"):
					JFrame n = new JFrame();
				ImageIcon ImageIcon13 = new ImageIcon(
						s+".jpg");
				Image Image13 = ImageIcon13.getImage();
				n.setIconImage(Image13);
				break;
				case ("Fence Guard Apprentice"):
					JFrame o = new JFrame();
				ImageIcon ImageIcon14 = new ImageIcon(
						s+".jpg");
				Image Image14 = ImageIcon14.getImage();
				o.setIconImage(Image14);
				break;
				case ("Beta The Magnet Warrior"):
					JFrame p = new JFrame();
				ImageIcon ImageIcon15 = new ImageIcon(
						s+".jpg");
				Image Image15 = ImageIcon15.getImage();
				p.setIconImage(Image15);
				break;
				case ("Alligator Sword"):
					JFrame q = new JFrame();
				ImageIcon ImageIcon16 = new ImageIcon(
						s+".jpg");
				Image Image16 = ImageIcon16.getImage();
				q.setIconImage(Image16);
				break;
				case ("Gamma The Magnet Warrior"):
					JFrame r = new JFrame();
				ImageIcon ImageIcon17 = new ImageIcon(
						s+".jpg");
				Image Image17 = ImageIcon17.getImage();
				r.setIconImage(Image17);
				break;
				case ("Celtic Guardian"):
					JFrame s1 = new JFrame();
				ImageIcon ImageIcon18 = new ImageIcon(
						s+".jpg");
				Image Image18 = ImageIcon18.getImage();
				s1.setIconImage(Image18);
				break;
				case ("Alpha The Magnet Warrior"):
					JFrame t = new JFrame();
				ImageIcon ImageIcon19 = new ImageIcon(
						s+".jpg");
				Image Image19 = ImageIcon19.getImage();
				t.setIconImage(Image19);
				break;
				case ("Harpie Lady"):
					JFrame u = new JFrame();
				ImageIcon ImageIcon20 = new ImageIcon(
						s+".jpg");
				Image Image20 = ImageIcon20.getImage();
				u.setIconImage(Image20);
				break;
				case ("Big Shield Gardna"):
					JFrame v = new JFrame();
				ImageIcon ImageIcon21 = new ImageIcon(
						s+".jpg");
				Image Image21 = ImageIcon21.getImage();
				v.setIconImage(Image21);
				break;
				case ("Witty Phantom"):
					JFrame w = new JFrame();
				ImageIcon ImageIcon22 = new ImageIcon(
						s+".jpg");
				Image Image22 = ImageIcon22.getImage();
				w.setIconImage(Image22);
				break;
				case ("Baby Dragon"):
					JFrame x = new JFrame();
				ImageIcon ImageIcon23 = new ImageIcon(
						s+".jpg");
				Image Image23 = ImageIcon23.getImage();
				x.setIconImage(Image23);
				break;
				case ("Cyber Jar"):
					JFrame y = new JFrame();
				ImageIcon ImageIcon24 = new ImageIcon(
						s+".jpg");
				Image Image24 = ImageIcon24.getImage();
				y.setIconImage(Image24);
				break;
				case ("Clown Zombie"):
					JFrame z = new JFrame();
				ImageIcon ImageIcon25 = new ImageIcon(
						s+".jpg");
				Image Image25 = ImageIcon25.getImage();
				z.setIconImage(Image25);
				break;
				case ("Time Wizard"):
					JFrame a1 = new JFrame();
				ImageIcon ImageIcon26 = new ImageIcon(
						s+".jpg");
				Image Image26 = ImageIcon26.getImage();
				a1.setIconImage(Image26);
				break;
				case ("Man-Eater Bug"):
					JFrame a2 = new JFrame();
				ImageIcon ImageIcon27 = new ImageIcon(
						s+".jpg");
				Image Image27 = ImageIcon27.getImage();
				a2.setIconImage(Image27);
				break;
				case ("Kuriboh"):
					JFrame a3 = new JFrame();
				ImageIcon ImageIcon28 = new ImageIcon(
						s+".jpg");
				Image Image28 = ImageIcon28.getImage();
				a3.setIconImage(Image28);
				break;
				case ("Mokey Mokey"):
					JFrame a4 = new JFrame();
				ImageIcon ImageIcon29 = new ImageIcon(
						s+".jpg");
				Image Image29 = ImageIcon29.getImage();
				a4.setIconImage(Image29);
				break;
				}

			
		}
	}

}
