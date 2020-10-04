// 리스트 6.2  PostsController.java

public class PostsController {

    private static final Logger logger
	= LoggerFactory.getLogger(PostsController.class);
    private PostRepository postRepository;

    @Value("${ipaddress}")
    private String ip;

    @Autowired
    public PostsController(PostRepository postRepository) {
	this.postRepository = postRepository;
    }
...
}
