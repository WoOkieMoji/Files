package controleur;




public interface Global {
	
	
	
	// **************** Propriétés ****************
	public static final int PORT = 6666;
	public static final String SEPARATOR = "//";
	public static final String CHEMIN = "media" + SEPARATOR ;
	public static final String CHEMINFONDS = CHEMIN + "fonds" + SEPARATOR ;
	public static final String FONDCHOIX = CHEMINFONDS + "fondchoix.jpg";
	public static final int GAUCHE = 0;  // pour la direction 
    public static final int DROITE = 1;
    public static final String CHEMINPERSOS = CHEMIN + "personnages" + SEPARATOR; 
    public static final String PERSO = CHEMINPERSOS + "perso"; 
    public static final String EXTIMAGE = ".gif"; 
    
    // MURS
    public static final int NBMURS = 20; // Nombre de murs placés aléatoirement sur l'arène
    public static final String CHEMINMURS = CHEMIN + "murs" + SEPARATOR;
    public static final String MUR = CHEMINMURS + "mur.gif"; // image du mur 
    public static final int H_MUR = 35; // hauteur de l’image
    public static final int L_MUR = 34; // largeur de l’image
	
	// les différents états 
    public static final String MARCHE = "marche"; 
    public static final String BLESSE = "touche"; 
    public static final String MORT = "mort"; 
	public static final int NBPERSOS = 3; 
	
	// taille de l’image du personnage 
	public static final int H_PERSO = 44; 
	public static final int L_PERSO = 39;
	
    public static final String SEPARE = "~";  // caractère de séparation
	public static final int PSEUDO = 0;
	
	// Dimensionnement de l'arène
	public static final int H_ARENE = 600 ;
	public static final int L_ARENE = 800;
	public static final int H_CHAT = 200;
	public static final int H_SAISIE = 25;
	public static final int MARGE = 5;
	
	// Image de fond
    public static final String FONDARENE = CHEMINFONDS + "fondarene.jpg";


































}
