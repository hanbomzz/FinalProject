package funding.dao.face;

import java.util.List;

import funding.dto.Project;
import funding.dto.Reward;

public interface ApplyDao {
	
	/**
	 * 프로젝트 최초 생성 
	 * 
	 * @param project
	 */
	public void projectInsert(Project project);
	
	
	/**
	 * 기본 리워드 생성 
	 * 
	 * @param projectNo
	 */
	public void insertDefaultReward(int projectNo);

	/**
	 * 입력하던 정보 불러오기
	 * 
	 * @param projectNo
	 * @return project - 입력했던 프로젝트 정보
	 */
	public Project projectSelect(int projectNo);

	/**
	 * 제품 정보 저장하기
	 * 
	 * @param project
	 */
	public void productUpdate(Project project);

	/**
	 * 입력했던 리워드 정보 불러오기
	 * 
	 * @param project
	 * @return list - 작성하던 리워드 정보
	 */
	public List<Reward> rewardSelect(int projectNo);

	/**
	 * 프로젝트에 저장되있던 대표사진 삭제
	 * 
	 * @param projectNo
	 */
	public void deleteProjectImage(int projectNo);
	
	/**
	 * 계획 정보 저장하기
	 * 
	 * @param project
	 */
	public void planUpdate(Project project);

	/**
	 * 프로젝트 정보 저장하기
	 * 
	 * @param project
	 */
	public void contentUpdate(Project project);


	
}
