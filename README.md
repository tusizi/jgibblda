# jgibblda

------

Open source LDA

## Usage

### 1.LDA

> Add following args to idea run LDA
-est -alpha 0.5 -beta 0.1 -ntopics 120 -niters 1000 -savestep 1000 -twords 20 -dfile newdocs.dat -dir /Users/codedrinker/Code/jgibblda/src/main/resources/models/casestudy-pro

### 2.Extract Topics 4 Each Document

> Add following args to idea run ExtractTopic4Document
-ntopics 5 -twords 18 -dtopicsFile model-final.topics -dthetaFile model-final.theta -dtwordsFile model-final.twords -dir /Users/codedrinker/Code/jgibblda/src/main/resources/models/casestudy-zh

### 2.Advance Combine Topics 4 Each Document

> Add following args to idea run AdvanceCombineTopics4Document
-ntopics 5 -twords 30 -tawords 20 -dtopicsFile model-final.topics -datopicsFile model-final.atopics -dthetaFile model-final.theta -dtwordsFile model-final.twords -dir /Users/codedrinker/Code/jgibblda/src/main/resources/models/casestudy-zh
------