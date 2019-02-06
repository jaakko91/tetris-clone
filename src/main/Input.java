package main;

public interface Input {
	void mousePressed(int button, int x, int y);
	void mouseReleased(int button, int x, int y);
	void keyPressed(int key, char c);
	void keyReleased(int key, char c);
}
