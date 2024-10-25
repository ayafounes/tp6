public class Document {
    protected String titre;
    protected int num_id;
    protected int num_pg;

    public Document(String titre, int num_id, int num_pg) {
        this.titre = titre;
        this.num_pg = num_pg;
        this.num_id = num_id;
    }

    public void Edition() {
        System.out.println("Titre: " + titre + ", Numéro d'identification: " + num_id + ", Nombre de pages: " + num_pg);
    }
}

class Article extends Document {
    private String nom_auteur;

    public Article(String titre, int num_id, int num_pg, String nom_auteur) {
        super(titre, num_id, num_pg);
        this.nom_auteur = nom_auteur;
    }

    public String getNom_auteur() {
        return nom_auteur;
    }

    public void Edition() {
        super.Edition();
        System.out.println("Nom d'auteur: " + nom_auteur);
    }
}

class Livre extends Article {
    private String nom_editeur;

    public Livre(String titre, int num_id, int num_pg, String nom_auteur, String nom_editeur) {
        super(titre, num_id, num_pg, nom_auteur);
        this.nom_editeur = nom_editeur;
    }

    public String getNom_editeur() {
        return nom_editeur;
    }


    public void Edition() {
        super.Edition();
        System.out.println("Nom de l'éditeur: " + nom_editeur);
    }
}

class Periodique extends Document {
    private int freq;

    public Periodique(String titre, int num_id, int num_pg, int freq) {
        super(titre, num_id, num_pg);
        this.freq = freq;
    }

    public int getFreq() {
        return freq;
    }

  
    public void Edition() {
        super.Edition();
        System.out.println("Fréquence de parution: " + freq);
    }
}

class Biblio {
    private Document[] listDoc;
    private int nb_doc;
    private int capacite;

    public Biblio(int capacite) {
        this.capacite = capacite;
        listDoc = new Document[capacite];
        nb_doc = 0;
    }

    int getCapacite() {
        return this.capacite;
    }

    void ajout_doc(Document d) {
        if (nb_doc < capacite) {
            listDoc[nb_doc] = d;
            nb_doc++;

       
            for (int i = 0; i < nb_doc - 1; i++) {
                for (int j = i + 1; j < nb_doc; j++) {
                    if (listDoc[i].num_id > listDoc[j].num_id) {
                        Document aux = listDoc[i];
                        listDoc[i] = listDoc[j];
                        listDoc[j] = aux;
                    }
                }
            }
        } else {
            System.out.println("Capacité atteinte");
        }
    }

    void Supprim_doc(int num) {
        boolean verif = false;
        int i = 0;
        while (i < nb_doc) {
            if (listDoc[i].num_id == num) {
                for (int j = i; j < nb_doc - 1; j++) {
                    listDoc[j] = listDoc[j + 1];
                }
                nb_doc--;
                verif = true;
                break;
            } else {
                i++;
            }
        }
        if (!verif) {
            System.out.println("Document non trouvé");
        }
    }

    public int getNombre_Document() {
        return nb_doc;
    }

    public Biblio liste_livre() {
        Biblio livres = new Biblio(capacite);

        for (int i = 0; i < nb_doc; i++) {
            if (listDoc[i] instanceof Livre) {
                livres.ajout_doc(listDoc[i]);
            }
        }

        return livres;
    }

    public Biblio liste_article() {
        Biblio articles = new Biblio(capacite);

        for (int i = 0; i < nb_doc; i++) {
            if (listDoc[i] instanceof Article && !(listDoc[i] instanceof Livre)) {
                articles.ajout_doc(listDoc[i]);
            }
        }

        return articles;
    }

    public Biblio liste_docsimples() {
        Biblio doc_simples = new Biblio(capacite);

        for (int i = 0; i < nb_doc; i++) {
            if (!(listDoc[i] instanceof Article) && !(listDoc[i] instanceof Periodique)) {
                doc_simples.ajout_doc(listDoc[i]);
            }
        }

        return doc_simples;
    }

    public Biblio liste_periodique() {
        Biblio per = new Biblio(capacite);

        for (int i = 0; i < nb_doc; i++) {
            if (listDoc[i] instanceof Periodique) {
                per.ajout_doc(listDoc[i]);
            }
        }

        return per;
    }
    class TestBiblio {
        public static void main(String[] args) {
            Biblio b = new Biblio(10);

            Document d = new Document("Doc1", 101, 200);
            Article a = new Article("Article1", 102, 150, "Auteur1");
            Livre l = new Livre("Livre1", 103, 300, "Auteur2", "Editeur1");
            Periodique p = new Periodique("Periodique1", 104, 50, 12);

            b.ajout_doc(d);
            b.ajout_doc(a);
            b.ajout_doc(l);
            b.ajout_doc(p);

            System.out.println("Liste complète des documents :");
            for (int i = 0; i < b.getNombre_Document(); i++) {
                b.listDoc[i].Edition();
            }

           
        }
    }
}





