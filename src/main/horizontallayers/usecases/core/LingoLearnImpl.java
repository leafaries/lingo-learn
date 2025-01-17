package horizontallayers.usecases.core;

import com.lingolearn.unclebob.horizontallayers.usecases.core.challenge.ChallengeManagerImpl;
import com.lingolearn.unclebob.horizontallayers.usecases.core.data.DataManagerImpl;
import com.lingolearn.unclebob.horizontallayers.usecases.core.preference.PreferencesManagerImpl;
import com.lingolearn.unclebob.horizontallayers.usecases.core.statistics.StatisticsManagerImpl;
import com.lingolearn.unclebob.horizontallayers.usecases.core.study.StudyManagerImpl;
import com.lingolearn.unclebob.horizontallayers.usecases.core.vocabulary.VocabularyManagerImpl;

/**
 * Main facade implementation that provides access to all LingoLearn subsystems.
 * Implements the Facade pattern to simplify client interaction with the complex
 * subsystems.
 */
public class LingoLearnImpl implements LingoLearn {
    private final VocabularyManager vocabularyManager;
    private final StudyManager studyManager;
    private final ChallengeManager challengeManager;
    private final StatisticsManager statisticsManager;
    private final DataManager dataManager;
    private final PreferencesManager preferencesManager;

    public LingoLearnImpl() {
        // Initialize all subsystem managers
        this.vocabularyManager = new VocabularyManagerImpl();
        this.studyManager = new StudyManagerImpl();
        this.challengeManager = new ChallengeManagerImpl();
        this.statisticsManager = new StatisticsManagerImpl();
        this.dataManager = new DataManagerImpl();
        this.preferencesManager = new PreferencesManagerImpl();
    }

    @Override
    public VocabularyManager vocabulary() {
        return vocabularyManager;
    }

    @Override
    public StudyManager study() {
        return studyManager;
    }

    @Override
    public ChallengeManager challenges() {
        return challengeManager;
    }

    @Override
    public StatisticsManager statistics() {
        return statisticsManager;
    }

    @Override
    public DataManager data() {
        return dataManager;
    }

    @Override
    public PreferencesManager preferences() {
        return preferencesManager;
    }
}